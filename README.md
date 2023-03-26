Codename One native Lib implementaton

Consists:

1. Custom TextFields (Android like MaterialTextFields) - applied in FeaturesCN Input Fields menu

2. Progress Animations 
    How to apply in CN1 ant project 
     - Build to generate ZTDesigns.cn1lib in dist folder
     - Copy ZTDesigns.cn1lib in lib folder of your CN1 project
     - Right click project and select Codename One then select Refresh cn1lib files
     
     Usage
   
     ```import com.zt.designs.Anims;
     
     Form form = new Form("", new BorderLayout());
     
     Form formAnim = new Form();
     formAnim.setLayout(new BorderLayout());
     formAnim.getAllStyles().setBgTransparency(0);
     formAnim.getAllStyles().setMargin(15, 15, 15, 15);
     formAnim.getToolbar().hideToolbar();
     
     Container cnt = new Container(BoxLayout.y());
     SpanLabel lblMsg = new SpanLabel("Processing request please wait", "lblProgressMsg");
     cnt.add(FlowLayout.encloseCenterMiddle(lblMsg));
        
     int size = 70;
     int width = 15;
      
      /**
	 *  @param formAnim - Form in which progress animation is added
	 *  @param size - progress bar size
	 *  @param width - progress bar width
	 *  @param bgColor - animation background color
	 *  @param progressColor - progress bar color
	 *  @param direction - progress direction (C - clockwise, A - anticlockwise)
	 *  @param duration - animation duration in miliseconds to determine
	 *  speed(the lower the duration the faster the progress speed)
	 */
   
     Anims.circleProgressIndicatorBar(formAnim, size, width, bgColor,
                progressColor, indicatorColor, "C", 10);
     cnt.add(formAnim);

     form.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(cnt));
     
     form.show();```




