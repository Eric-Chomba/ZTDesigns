/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zt.designs;

import com.codename1.components.SpanLabel;

import com.codename1.io.Log;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Stroke;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.GeneralPath;
import static com.codename1.ui.layouts.BorderLayout.CENTER;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.UITimer;

/**
 *
 * @author Eric
 */
public class Anims {

    static int count1 = 0, count1B = 0, count2 = 0, count3 = 0, radCount = 0,
            currentProgress = 10, currentProgress2 = -10,
            fixedCount = 0, dimen, duration;
    static int start, end, start2, end2;
    static int waveCount, dimenWave, waveCount3, waveCount4,
            bubbleCycle = 1, scanCount = 0;
    static int cycle = 1;
    static ArrayList<Container> cntWave3Arr;
    static boolean topScan = true, showCircleBars = false;

    /**
     * @param formAnim - Form in which progress animation is added
     * @param size - progress bar size
     * @param width - progress bar width
     * @param bgColor - animation background color
     * @param progressColor - progress bar color
     * @param direction - progress direction (C - clockwise, A - anticlockwise)
     * @param duration - animation duration in miliseconds to determine
     * speed(the lower the duration the faster the progress speed)
     */
    public static void circleProgressBar(Form formAnim, int size, int width,
            int bgColor, int progressColor, String direction, int duration) {

        formAnim.setLayout(new BorderLayout());

        Form formProgress = new Form(new BorderLayout());
        formProgress.getAllStyles().setBgColor(0);
        formProgress.getToolbar().hideToolbar();

        formAnim.add(CENTER, FlowLayout.encloseCenterMiddle(
                createCircleProgress(formAnim, formProgress, size, width, bgColor,
                        progressColor, direction, duration
                ))
        );

        formAnim.revalidate();

    }

    protected static Form createCircleProgress(Form formAnim, Form formProgress,
            int size, int width, int bgColor, int progressColor,
            String direction, int duration) {

        Container cntPar = new Container(BoxLayout.y());
        Container cntMask = new Container(new LayeredLayout());

        // 0xff000000 0x0000FF 0x15E7FF 0x3399ff
        Image roundMask = Image.createImage(size, size, 0);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(progressColor);

        if (direction.equals("A")) {
            switch (cycle) {
                case 1:
                    gr.fillArc(0, 0, size, size, 120, currentProgress);
                    break;
                case 2:
                    gr.fillArc(0, 0, size, size, 240, currentProgress);
                    break;
                case 3:
                    gr.fillArc(0, 0, size, size, 345, currentProgress);
                    break;
            }
        } else if (direction.equals("C")) {
            switch (cycle) {
                case 1:
                    gr.fillArc(0, 0, size, size, 60, currentProgress);
                    break;
                case 2:
                    gr.fillArc(0, 0, size, size, 245, currentProgress);
                    break;
                case 3:
                    gr.fillArc(0, 0, size, size, 120, currentProgress);
                    break;
            }
        }
        Object mask = roundMask.createMask();
        roundMask.applyMask(mask);
        Label lbl = new Label(roundMask);
        cntMask.add(FlowLayout.encloseCenterMiddle(lbl));

        int bgSize = size - width;
        Image roundMask2 = Image.createImage(bgSize, bgSize, 0);
        Graphics gr2 = roundMask2.getGraphics();
        gr2.setColor(bgColor);
        gr2.fillArc(0, 0, bgSize, bgSize, 0, 360);

        Object mask2 = roundMask2.createMask();
        roundMask2.applyMask(mask2);
        Label lbl2 = new Label(roundMask2);
        cntMask.addAll(FlowLayout.encloseCenterMiddle(lbl2));
        cntPar.add(cntMask);

        formProgress.add(CENTER, cntPar);

        //proc.printLine("Progress " + currentProgress);
        new UITimer(() -> {

            if (direction.equals("A")) {
                if (currentProgress < 361) {
                    currentProgress = currentProgress + 10;
                } else if (currentProgress == 370) {
                    currentProgress = 10;
                    //Log.p("ZT A Cycle=" + cycle);
                    switch (cycle) {
                        case 1:
                            cycle = 2;
                            break;
                        case 2:
                            cycle = 3;
                            break;
                        case 3:
                            cycle = 1;
                            break;
                    }
                }
            } else if (direction.equals("C")) {

                if (currentProgress > -361) {
                    currentProgress = currentProgress - 10;
                } else if (currentProgress == -370) {
                    currentProgress = 10;
                    //Log.p("ZT C Cycle=" + cycle);
                    switch (cycle) {
                        case 1:
                            cycle = 2;
                            break;
                        case 2:
                            cycle = 3;
                            break;
                        case 3:
                            cycle = 1;
                            break;
                    }
                }
            }

            circleProgressBar(formAnim, size, width, bgColor, progressColor, direction, duration);

        }).schedule(duration, false, formProgress);

        return formProgress;
    }

    public static void circleProgressIndicatorBar(Form formAnim, int size, int width, int bgColor,
            int progressBarColor, int indicatorColor, String direction, int duration) {

        formAnim.setLayout(new BorderLayout());

        Form formProgress = new Form(new BorderLayout());
        formProgress.getAllStyles().setBgColor(bgColor);
        //formProgress.getAllStyles().setMargin(15, 15, 15, 15);
        formProgress.getToolbar().hideToolbar();

        formAnim.add(CENTER, FlowLayout.encloseCenterMiddle(
                circleProgressIndicator(formAnim, formProgress, size, width, bgColor,
                        progressBarColor, indicatorColor, direction, duration
                ))
        );
        formAnim.revalidate();

    }

    protected static Form circleProgressIndicator(Form formAnim, Form formProgress, int size, 
            int width, int bgColor,
            int progressBarColor, int indicatorColor, String direction, int duration) {

        Container cntPar = new Container(BoxLayout.y());
        Container cntMask = new Container(new LayeredLayout());

        // 0xff000000 0x0000FF 0x15E7FF 0x3399ff
        Image roundMask = Image.createImage(size, size, 0);
        Graphics gr = roundMask.getGraphics();

        if (direction.equals("A")) {
            start = currentProgress; //10 20
            end = currentProgress + 50; //60 70
            start2 = end; //60 70
            end2 = (360 - end) + currentProgress; //310 310
            //end2 = 360 + start; //350 340
            //end2=360;

            /*#15E7FF  #BDE2EF  #C8E8F3  #D1EBF4*/
            gr.setColor(indicatorColor);
            gr.fillArc(0, 0, size, size, start, end);
            gr.setColor(progressBarColor);
            gr.fillArc(0, 0, size, size, start2, end2);

            //proc.printLine("Progress=" + currentProgress + " Start=" + start + " End=" + end
            // + " Start2=" + start2 + " End2=" + end2);
        } else if (direction.equals("C")) {
            start = currentProgress2; //-10 -20
            end = currentProgress2 - 50; //-60 -70
            start2 = end; //-60 -70
            //end2 = (-360 - end) - currentProgress2; //-310 -310
            end2 = -310;

            gr.setColor(indicatorColor);
            gr.fillArc(0, 0, size, size, start, end);
            gr.setColor(progressBarColor);
            gr.fillArc(0, 0, size, size, start2, end2);

            //proc.printLine("Progress=" + currentProgress2 + " Start=" + start + " End=" + end
            // + " Start2=" + start2 + " End2=" + end2);
        }

        //if (direction.equals("A")) {
        // if (start == 10 && end == 60) {
        /* gr.setColor(0xFF0000);
            gr.fillArc(0, 0, dimen, dimen, start, end);
            gr.setColor(0x0000FF);
            gr.fillArc(0, 0, dimen, dimen, start2, end2);*/
        //}
//            if (start == 60 && end == 110) {
//                gr.setColor(0xFF0000);
//                gr.fillArc(0, 0, dimen, dimen, start, end);
//                start = end;
//                end = end + 50;
//            } else {
//                gr.setColor(0x0000FF);
//                gr.fillArc(0, 0, dimen, dimen, 110, 360);
//            }
        //}
        Object mask = roundMask.createMask();
        roundMask.applyMask(mask);
        Label lbl = new Label(roundMask);
        cntMask.add(FlowLayout.encloseCenterMiddle(lbl));

        int bgSize = size - width;

        Image roundMask2 = Image.createImage(bgSize, bgSize, 0);
        Graphics gr2 = roundMask2.getGraphics();
        //if (proc.getDarkMode().equals("On")) {
        gr2.setColor(bgColor);
//        } else {
//            gr2.setColor(0xffffff);
//        }
        //gr2.setColor(0xff000000); //0xffffff 0x15E7FF 0xff000000

        //gr2.fillArc(0, 0, 175, 175, 0, 360);
        //if (direction.equals("A")) {
        //gr2.fillArc(0, 0, dimen2, dimen2, 120, currentProgress);
        gr2.fillArc(0, 0, bgSize, bgSize, 0, 360);
        //} else if (direction.equals("C")) {
        //gr2.fillArc(0, 0, dimen2, dimen2, 60, currentProgress);
        //}

        //gr2.fillArc(0, 0, 175, 175, -10, -360);
        Object mask2 = roundMask2.createMask();
        roundMask2.applyMask(mask2);
        Label lbl2 = new Label(roundMask2);
        cntMask.addAll(FlowLayout.encloseCenterMiddle(lbl2));
        cntPar.add(FlowLayout.encloseCenterMiddle(cntMask));

        //formAnim.add(CENTER, FlowLayout.encloseCenterMiddle(cntPar));
        formProgress.add(CENTER, FlowLayout.encloseCenterMiddle(cntPar));

        new UITimer(() -> {

            if (direction.equals("A")) {
                if (currentProgress < 361) {
                    currentProgress = currentProgress + 10;
                } else if (currentProgress == 370) {
                    currentProgress = 10;
                    //start = 10;
                    //end = 60;
                }
            } else if (direction.equals("C")) {
//             if (currentProgress > -281) {
//                currentProgress = currentProgress - 10;
//            } else if (currentProgress == -290) {
//                currentProgress = 60;
//            }
                if (currentProgress2 > -361) {
                    currentProgress2 = currentProgress2 - 10;
                } else if (currentProgress2 == -370) {
                    currentProgress2 = -10;
                    // start = -10;
                    //end = -60;
                }
            }

            circleProgressIndicatorBar(formAnim, size, width, bgColor,
                    progressBarColor, indicatorColor, direction, duration);

        }).schedule(duration, true, formProgress);

        return formProgress;
    }

    public static void clockCircleProgressBar(Form formAnim, int size, int barColor, int indicatorColor, int progressDuration) {

        //Log.p("clockCircleProgressBar invoked");

        showCircleBars = true;
        CircleClockBars bars = new CircleClockBars(formAnim, size, barColor, indicatorColor, 150);
        bars.setUIID("CircleBars");
        formAnim.add(CENTER, bars);
        formAnim.revalidate();
    }

    protected static class CircleClockBars extends Component {

        Form formAnim;
        int size, barColor, indicatorColor, progressDuration;

        public CircleClockBars(Form formAnim, int size, int barColor, int indicatorColor,
                int progressDuration) {

            //Log.p("CircleClockBars invoked");

            this.formAnim = formAnim;
            this.size = size;
            this.barColor = barColor;
            this.indicatorColor = indicatorColor;
            this.progressDuration = progressDuration;
        }

        @Override
        public Dimension calcPreferredSize() {
            return new Dimension(size, size);
        }

        @Override
        public void paintBackground(Graphics graphics) {

            //Log.p("init count 5");
            //super.paintBackground(graphics);
            //graphics.setAntiAliased(true);
            double padding = 10;

            //clock radius
            double r = Math.min(getWidth(), getHeight()) / 2 - padding;

            //center point
            double cX = getX() + getWidth() / 2;
            double cY = getY() + getHeight() / 2;

            int tickLen; //at the quarters //0xCCCCCC 0x0000FF 0x3399ff
//
            ArrayList<Stroke> strokeArr = new ArrayList<>();
            ArrayList<GeneralPath> pathArr = new ArrayList<>();

            //Log.p("init count 6");
            int max = 10;
            float strokeWidth;
            if (Display.getInstance().isSimulator()) {
                strokeWidth = 20f;
            } else if (Display.getInstance().isDesktop()) {
                strokeWidth = 3f;
            } else {
                strokeWidth = 10f;
            }
            //Draw tick for each sec 1-10
            for (int j = 1; j <= max; j++) {

                Stroke tickStroke;
                if (Display.getInstance().isDesktop()) {
                    tickLen = 5;//5 10
                    //strokeWidth, strokeStyle, strokeJoinStyle, miterLimit
                    tickStroke = new Stroke(strokeWidth, Stroke.CAP_SQUARE,
                            Stroke.JOIN_ROUND, 10f); //5f 1f 10f
                } else {
                    tickLen = 15;
                    tickStroke = new Stroke(strokeWidth, Stroke.CAP_SQUARE,
                            Stroke.JOIN_ROUND, 3f);
                }
                strokeArr.add(tickStroke);

                GeneralPath ticksPath = new GeneralPath();
                //convert tick num to double
                double dj = (double) j;

                //Get angle from 12 0'clock to this tick (radians)
                double angleFrom12 = dj / 10.0 * 2.0 * Math.PI;

                //Get angle from 3 0'clock to this tick
                double angleFrom3 = Math.PI / 2.0 - angleFrom12;

                //Move to the outer edge of the circle
                ticksPath.moveTo(
                        (float) (cX + Math.cos(angleFrom3) * r),
                        (float) (cY - Math.sin(angleFrom3) * r)
                );
                //Draw line inward along radius for length of the tick mark
                ticksPath.lineTo(
                        (float) (cX + Math.cos(angleFrom3) * (r - tickLen)),
                        (float) (cY - Math.sin(angleFrom3) * (r - tickLen))
                );
                pathArr.add(ticksPath);
            }

            Log.p("FixedCountAlpha=" + fixedCount);
            Log.p("PathArrSize=" + pathArr.size() + " StrokeArrSize=" + strokeArr.size());

            for (int g = 0; g < pathArr.size(); g++) {

                //int drawCount = 0;
                if (g == fixedCount) {
                    Log.p("FixedCountHgh=" + fixedCount);
                    graphics.setColor(indicatorColor);
                    //drawCount = fixedCount;
                    graphics.drawShape(pathArr.get(fixedCount), strokeArr.get(fixedCount));
                } else {

                    //Log.p("LoopCount---------------------=" + loopCount);
                    //if (loopCount <= 9) {
                    Log.p("FixedCount=" + fixedCount);
                    graphics.setColor(barColor); //0xFF0000
                    //drawCount = g;
                    graphics.drawShape(pathArr.get(g), strokeArr.get(g));
                    //}
                }
                //graphics.drawShape(pathArr.get(drawCount), strokeArr.get(drawCount));
            }

            new UITimer(() -> {
                fixedCount = fixedCount + 1;
                if (fixedCount == max) {
                    fixedCount = 0;
                }

                if (showCircleBars) {
                    clockCircleProgressBar(formAnim, size, barColor, indicatorColor, progressDuration);
                }
                //Log.p("circleAnim2 invoked");

            }).schedule(progressDuration, false, formAnim);
        }
    }

}
