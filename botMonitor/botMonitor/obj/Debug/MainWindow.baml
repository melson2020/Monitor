   M S B A M L   `   `   `  ����E  AbotMonitor, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:botMonitor
botMonitor  5   	       botMonitor.MainWindow   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:botMonitor     5   	   -   5          � 
ResizeMode$  CanMinimize=�5   )      Disabled#���   5   F    � Title$ Bot Monitor��5   	   $	��450��6   $	��600��6*   .��5	      ]� ���#242C37.+�5
   
   � ���	#553399ff � RowDefinitions 5      �� 5      $��45 �6    �� 5      $��35 �6    �� 5      $��35 �6    �� 5      $��35 �6    �� 5       � ColumnDefinitions 5      �� 5      �� 5      .+�5      �� $��0��6   $��2��6%   $��Center=�69   $��Center=�6T   $��20(�6q   $��Computer Properties��6   $
��Bold"�6�   
���   �6�   �� 5      -   5         ��Name$ ConnectStatusBorder��5   �   $��0��6   $��1��6"   $��20��62   $��20��6=   $��Right=�6I   $��Center=�6e   $��5q�6�    �� CornerRadius$ 15��6�   6�   �� 5      $��1��6   $��0��6&   $��Center=�66    �� Orientation$ 
Horizontal=�6Q   .+�5      �� $��Center=�6   $��14(�67   $��CPU Count :��6E   $��5q�6X     Primary#���  6c   �� 5      -   5         ��Name$ CPU_Text��5   P   $��Center=�6   $��14(�67   $��5q�6E   6d   5      �� 5      $��1��6   $��1��6'   $��Center=�67   $ 
Horizontal=�6R   .+�5      �� $��Center=�6   $��14(�67   $��
Bot Name :��6E   $��5q�6W   #���  6b   �� 5      -   5        $ HostName_Text��5   P   $��Center=�6   $��14(�67   $��5q�6E   6i   5      �� 5       $��2��6   $��0��6&   $��Center=�66   $ 
Horizontal=�6Q   .+�5!      �� $��Center=�6   $��14(�67   $��CPU UseRate��6E   $��5q�6X   #���  6c   �� 5"      -   5        $ CPUUseRate_Text��5"   P   $��Center=�6   $��14(�67   $��5q�6E   6k   5#      �� 5$      $��2��6   $��1��6&   $��Center=�66   $ 
Horizontal=�6Q   .+�5%      �� $��Center=�6   $��14(�67   $
��IP :��6E   $��5q�6Q   #���  6\   �� 5&      -   5        $ IP_Text��5&   P   $��Center=�6   $��14(�67   $��5q�6E   6c   5'      �� 5(      $��3��6   $��0��6&   $��Center=�66   $ 
Horizontal=�6Q   .+�5)      �� $��Center=�6   $��14(�67   $��RAM UseRate :��6E   $��5q�6Z   #���  6e   �� 5*      -   5        $ RAMUseRate_Text��5*   P   $��Center=�6   $��14(�67   $��5q�6E   6k   5+      �� 5,      $��3��6   $��1��6&   $��Center=�66   $ 
Horizontal=�6Q   .+�5-      �� $��Center=�6   $��14(�67   $��Resolution :��6E   $��5q�6Y   #���  6d   �� 5.      -   5        $ Resolution_Text��5.   P   $��Center=�6   $��14(�67   $��5q�6E   6k   5/      &   System.Windows.Controls.DataGrid  50      -	   5          Name$ ProcessGrid��50   '   $��0��6   $��4��6<   	   GridLinesVisibility$
	 None=�6K   $
��Left=�6f   
   AutoGenerateColumns
 .    DataGridStyle#���  6�      HeadersVisibility$ Column=�6�   $��0q�6  ���	#55171B27$��5q�61  ��6�   �� *
OrderInfos+   Columns 51      0  *System.Windows.Controls.DataGridTextColumn  52      ,  &System.Windows.Controls.DataGridColumn   Header$ ID��6)      Width5  /System.Windows.Controls.DataGridLengthConverter$ * 65   1  +System.Windows.Controls.DataGridBoundColumn   Binding 6?   �� *	ProcessId+6`     53      $
 NAME��6)   $ 2* 67    6B   �� *WindowTitle+6e     54      $ STATUS��6)   $ * 69    6C   �� *ProcessRunningStatus+6o   56      �� 57      -
   5         ��Name$ TextBox��57      $��3q�6'   $��11(�63    �� TextWrapping$
 Wrap=�6A    �� AcceptsReturn . $��4��6j   $��1��6w   $��5q�6�   ���	#55171B27
�������" �� VerticalScrollBarVisibility$
 Auto=�6�    �� 
IsReadOnly . .��6�   58      59      5:      5;      