   M S B A M L   `   `   `  ���� E  AbotMonitor, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:botMonitor
botMonitor      botMonitor.MainWindow   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     @d2http://schemas.microsoft.com/expression/blend/2008     Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     *localclr-namespace:botMonitor     -     � 
ResizeMode$  CanMinimize=�   Disabled#���    � Title$ Bot Monitor��$	��450��$	��600��.��]� ���#242C37.+�� ���	#553399ff � RowDefinitions �� $��45 ��� $��35 ��� $��35 ��� $��35 ���  � ColumnDefinitions �� �� .+��� $��0��$��2��$��Center=�$��Center=�$��20(�$��Computer Properties��$
��Bold"�
���   ��� -    ��Name$ ConnectStatusBorder��$��0��$��1��$��20��$��20��$��Right=�$��Center=�$��5q� �� CornerRadius$ 15���� $��1��$��0��$��Center=� �� Orientation$ 
Horizontal=�.+��� $��Center=�$��14(�$��CPU Count :��$��5q�  Primary#���  �� -    ��Name$ CPU_Text��$��Center=�$��14(�$��5q��� $��1��$��1��$��Center=�$ 
Horizontal=�.+��� $��Center=�$��14(�$��
Bot Name :��$��5q�#���  �� -   $ HostName_Text��$��Center=�$��14(�$��5q��� $��2��$��0��$��Center=�$ 
Horizontal=�.+��� $��Center=�$��14(�$��CPU UseRate��$��5q�#���  �� -   $ CPUUseRate_Text��$��Center=�$��14(�$��5q��� $��2��$��1��$��Center=�$ 
Horizontal=�.+��� $��Center=�$��14(�$
��IP :��$��5q�#���  �� -   $ IP_Text��$��Center=�$��14(�$��5q��� $��3��$��0��$��Center=�$ 
Horizontal=�.+��� $��Center=�$��14(�$��RAM UseRate :��$��5q�#���  �� -   $ RAMUseRate_Text��$��Center=�$��14(�$��5q��� $��3��$��1��$��Center=�$ 
Horizontal=�.+��� $��Center=�$��14(�$��Resolution :��$��5q�#���  �� -   $ Resolution_Text��$��Center=�$��14(�$��5q�&   System.Windows.Controls.DataGrid  -	     Name$ ProcessGrid��$��0��$��4��	   GridLinesVisibility$
	 None=�$
��Left=�
   AutoGenerateColumns
 .    DataGridStyle#���     HeadersVisibility$ Column=�$��0q����	#55171B27$��5q����� *
OrderInfos+   Columns 0  *System.Windows.Controls.DataGridTextColumn  ,  &System.Windows.Controls.DataGridColumn   Header$ ID��   Width5  /System.Windows.Controls.DataGridLengthConverter$ * 1  +System.Windows.Controls.DataGridBoundColumn   Binding �� *	ProcessId+  $
 NAME��$ 2*  �� *WindowTitle+  $ STATUS��$ *  �� *ProcessRunningStatus+�� -
    ��Name$ TextBox��$��3q�$��11(� �� TextWrapping$
 Wrap=� �� AcceptsReturn . $��4��$��1��$��5q����	#55171B27
�������" �� VerticalScrollBarVisibility$
 Auto=� �� 
IsReadOnly . .��