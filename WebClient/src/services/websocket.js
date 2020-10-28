export function wsConnect(dispatch) {
  const ws = new WebSocket("ws:13.75.126.156:49300/websocket");
  ws.onopen = () => {
    console.log('webSocket on open, ws:13.75.126.156:49300/websocket')
  };

  ws.onmessage = ({ data }) => {
    let result = JSON.parse(data);
    let type = result.componentName;
    switch (type) {
      case "bots":
        dispatch({ type: "timer/getBots", payload: { page: 1 } });
        break;
      case "resourceTable":
        let time = new Date().getTimezoneOffset() / 60;
        dispatch({
          type: "timer/fleshResource",
          payload: { requestTimeZone: time }
        });
        dispatch({
          type: "control/findPendingSessions"
        });
        break;
      default:
        break;
    }
  };

  ws.onclose = function(e) {
  
  };
  
  ws.onerror=(e)=>{
    console.log('webSocket on Error, websocket init failed')
  }
}
