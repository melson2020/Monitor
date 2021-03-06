package com.fast.bpserver.service.impl;

import com.fast.bpserver.base.AbstractService;
import com.fast.bpserver.dao.IBPAProcessDao;
import com.fast.bpserver.entity.BPAProcess;
import com.fast.bpserver.entity.QueryVo.BPASessionLogs;
import com.fast.bpserver.entity.vo.BPAProcessVo;
import com.fast.bpserver.entity.vo.ErrorChartVo;
import com.fast.bpserver.entity.vo.ProcessErrorInfoVo;
import com.fast.bpserver.entity.vo.ProcessErrorLogVo;
import com.fast.bpserver.service.IBPAProcess;
import com.fast.bpserver.utils.TimeZoneUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Nelson on 2019/10/24.
 */
@Service
public class IBPAProcessImpl extends AbstractService<BPAProcess> implements IBPAProcess {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   @Autowired
   private IBPAProcessDao processDao;
    @Override
    public JpaRepository<BPAProcess,String> getRepository() {
        return processDao;
    }

    @Override
    public List<BPAProcess> findByProcessType(String type){
        return processDao.findByProcessType(type);
    }

    public  List<BPAProcess> findAll(){
        return processDao.findAll();
    }

    //根据session 以及session log 生成ProcessVo
    public List<BPAProcessVo> GenerateUnCompetedProcessVos(List<BPASessionLogs> uncompletedSessionList, Map<String,BPAProcess> processMap,Date queryDate,Date now,Integer timeZone){
        List<BPAProcessVo> unCompletedVos=GenrateProcessVoWithUncompletedSession(uncompletedSessionList,processMap,queryDate,now,timeZone);
        return unCompletedVos;
    }

    private List<BPAProcessVo> GenrateProcessVoWithUncompletedSession(List<BPASessionLogs> uncompletedSessionList, Map<String,BPAProcess> processMap,Date queryDate,Date now,Integer timeZone){
        Map<String,BPAProcessVo> voMap=new HashMap<>();
        //按照processId 分类
        Map<String,List<BPASessionLogs>> sessionLogMap=new HashMap<>();
        for (BPASessionLogs sessionLog:uncompletedSessionList){
              List<BPASessionLogs> sessionLogsList=sessionLogMap.get(sessionLog.getProcessid());
              if(sessionLogsList==null){
                  sessionLogsList=new ArrayList<>();
              }
              sessionLogsList.add(sessionLog);
              sessionLogMap.put(sessionLog.getProcessid(),sessionLogsList);
              String key=sessionLog.getProcessid();
              BPAProcessVo vo=voMap.get(key);
              BPAProcess process=processMap.get(key);
              if(process==null)continue;
              if(vo==null){
                  vo=new BPAProcessVo(process.getProcessid(),process.getName(),0,0);
                  voMap.put(vo.getProcessId(),vo);
              }
            SetProcessVoError(sessionLog.getSessionid(),sessionLog.getResult(),sessionLog.getEnddatetime(),vo,timeZone);
        }
        List<BPAProcessVo> processVoList=new ArrayList<>(voMap.values());
        for (BPAProcessVo vo:processVoList){
            SetProcessErrorList(vo,sessionLogMap.get(vo.getProcessId()),queryDate,now,timeZone);
        }
        return processVoList;
    }


    /**
     *设置processVo error数据
     */
    private void SetProcessVoError(String stageName,String result,Date endTime,BPAProcessVo vo,Integer timeZone){
        Boolean definedError=StringUtils.isEmpty(stageName)?false:(stageName.toUpperCase().startsWith("BE:")||stageName.toUpperCase().startsWith("SE:"));
        Boolean undefinedError=StringUtils.isEmpty(result)?false:result.toUpperCase().contains("ERROR:");
        if(vo.getLastTime()==null||(endTime!=null&&vo.getLastTime().getTime()<=endTime.getTime())){
            vo.setLastTime(TimeZoneUtil.formateDateToZone(endTime,timeZone));
        }
        if(definedError||undefinedError){
            vo.setErrorCount(vo.getErrorCount()==null?1:vo.getErrorCount()+1);
            if(undefinedError){
                vo.setUndefinedCount(vo.getUndefinedCount()==null?1:vo.getUndefinedCount()+1);
            }
        }
    }

    /**
     * 设置processVo 的3个子集合
     */
    private void SetProcessErrorList(BPAProcessVo vo,List<BPASessionLogs> subSessionLogs,Date queryDate,Date now,Integer timeZone){
        if(subSessionLogs==null||subSessionLogs.size()<=0)return;
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd:HH");
         List<ErrorChartVo> errorChartVoList= CreatedLast24TimeSpanChart(queryDate,now,timeZone);
         Integer hours=errorChartVoList.get(0).getTimeSpan();
         Map<String,ProcessErrorInfoVo> errorCodeMap=new HashMap<>();
         //设置查询时间保留至小时，计算error 所处区间使用
         Calendar c=Calendar.getInstance();
         c.setTime(queryDate);
         c.set(Calendar.MINUTE,0);
         c.set(Calendar.SECOND,0);
         Date queryDateToHour=c.getTime();
         List<ProcessErrorLogVo> logs=new ArrayList<>();
//         Map<String,ProcessErrorLogVo> logMap=new HashMap<>();
         Date requestStopTime=new Date(0);
         int statusid=0;
         int i=0;
         for(BPASessionLogs log:subSessionLogs){
             //是否含有error
             if(log.getStoprequested()!=null&&log.getStoprequested().getTime()>requestStopTime.getTime()){
                 requestStopTime=log.getStoprequested();
             }
             if(log.getStatusid()==3)statusid=log.getStatusid();
             String errorType=getErrorType(log.getStagename(),log.getResult());
             if(errorType!=null){
                int index=(int)(log.getStartdatetime().getTime()-queryDateToHour.getTime())/(hours*60*60*1000);
                 //设置 errorChart
                 index=index>23?23:index;
                 ErrorChartVo errorChartVo=errorChartVoList.get(index);
                 errorChartVo.setErrorCount(errorChartVo.getErrorCount()+1);
                //设置 errorType
                 ProcessErrorInfoVo errorCode=errorCodeMap.get(errorType);
                 String description=errorType.equals("unDefined")?"Error detail in logs":"defined error,error description should be defined";
                 if(errorCode==null){
                     errorCode=new ProcessErrorInfoVo(errorType,1,description);
                     errorCodeMap.put(errorType,errorCode);
                 }else {
                     errorCode.setErrorCount(errorCode.getErrorCount()+1);
                 }
                 //设置 log
                 logs.add(new ProcessErrorLogVo(i,sdf.format(TimeZoneUtil.formateDateToZone(log.getStartdatetime(),timeZone)),log.getStagename(),log.getResult()));
                 i++;
             }
         }
        vo.setErrorChart(errorChartVoList);
        vo.setErrorCodes(new ArrayList<>(errorCodeMap.values()));
        vo.setLogs(logs);
         //当该process 无error 时  设置request time 或者 terminated 时间
        if(vo.getErrorCount()<=0&&requestStopTime.getTime()>0){
            vo.setRequestedStoped(true);
            vo.setRequestedTime(sdf.format(requestStopTime));
        }
        if(vo.getErrorCount()<=0&&statusid==3){
            vo.setTerminated(true);
        }
    }

    private String getErrorType(String stageName,String result){
        Boolean definedError=StringUtils.isEmpty(stageName)?false:(stageName.toUpperCase().startsWith("BE:")||stageName.toUpperCase().startsWith("SE:"));
        Boolean undefinedError=StringUtils.isEmpty(result)?false:result.toUpperCase().contains("ERROR:");
        if(definedError){
            String errorCode=stageName.split(" ")[0];
            return errorCode==null?stageName:errorCode;
        }
        if(undefinedError){
            return "unDefined";
        }
        return null;
    }

    private List<ErrorChartVo> CreatedLast24TimeSpanChart(Date quertDate,Date timeNow,Integer timeZone){
        int hours=(int)Math.ceil((timeNow.getTime()-quertDate.getTime())*1.0/(24*1000*60*60.0));
        SimpleDateFormat simpleDateFormat=hours>1?new SimpleDateFormat("dd-HH"):new SimpleDateFormat("HH");
        List<ErrorChartVo> chartVoList=new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(quertDate);
        for (int i=0;i<24;i++){
            Date beginDate=calendar.getTime();
            calendar.add(Calendar.HOUR_OF_DAY,hours);
            String beginStr=simpleDateFormat.format(TimeZoneUtil.formateDateToZone(beginDate,timeZone));
            ErrorChartVo vo=new ErrorChartVo(beginStr,0,hours,i);
            chartVoList.add(vo);
        }
        chartVoList.sort(new Comparator<ErrorChartVo>() {
            @Override
            public int compare(ErrorChartVo o1, ErrorChartVo o2) {
                return o1.getSortNo()<o2.getSortNo()?0:1;
            }
        });
       return chartVoList;
    }
}
