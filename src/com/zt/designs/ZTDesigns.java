/*
 * Copyright comment here
 */
package com.zt.designs;

import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Stroke;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.DefaultLookAndFeel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UITimer;

/**
 * This is a demo class to help you get started building a library
 *
 * @author Eric
 */
public class ZTDesigns {

    static int count1 = 0, count1B = 0, count2 = 0, count3 = 0, radCount = 0,
            currentProgress = 10, currentProgress2 = -10,
            fixedCount = 0, dimen, duration;
    int start, end, start2, end2;
    int waveCount, dimenWave, waveCount3, waveCount4,
            bubbleCycle = 1, scanCount = 0;
    static int cycle = 1;
    ArrayList<Container> cntWave3Arr;
    boolean topScan = true, showCircleBars = false;

    /**
     * Design form title bar & background
     *
     * @param title
     * @param prevForm
     * @return
     */
    public static Form getForm(String title, Form prevForm) {

        Form form = new Form(title);
        form.getAllStyles().setBgColor(0xffffff);

        Font font;
        if (Display.getInstance().isTablet()) {
            font = Font.createTrueTypeFont("native:MainLight",
                    "native:MainLight").derive(Display.getInstance()
                            .convertToPixels(5), Font.STYLE_PLAIN);
        } else {
            font = Font.createTrueTypeFont("native:MainLight",
                    "native:MainLight").derive(Display.getInstance()
                            .convertToPixels(3), Font.STYLE_PLAIN);
        }

        /*form.setLayout(BoxLayout.y());
        form.getToolbar().hideToolbar();
        Container cntTitle = new Container(new BorderLayout());
        cntTitle.getAllStyles().setBgTransparency(255);
        cntTitle.getAllStyles().setBgColor(0x15E7FF);
        Image back = materialIcon(FontImage.MATERIAL_ARROW_BACK).toImage();
        Button btn = new Button(back);
        btn.addActionListener(e -> {
            prevForm.showBack();
        });
        Label lblTitle = new Label(title);
        lblTitle.getAllStyles().setFont(font);
        lblTitle.getAllStyles().setFgColor(0xffffff);
        cntTitle.add(BorderLayout.WEST, FlowLayout.encloseLeftMiddle(btn))
                .add(BorderLayout.CENTER,
                        FlowLayout.encloseCenterMiddle(lblTitle));
        
        form.add(cntTitle);*/
        form.getToolbar().getAllStyles().setBgTransparency(255);
        form.getToolbar().getAllStyles().setBgColor(0x15E7FF); //15E7FF 3399ff 
        form.getToolbar().getAllStyles().setBorder(Border.createEmpty());
        form.getToolbar().getAllStyles().setPadding(0, 0, 0, 0);
        form.getToolbar().getAllStyles().setMargin(0, 0, 0, 0);

        //form.getToolbar().setUIID("tbar");
        //border: none;
        //background-color: #15E7FF;
        //padding: 0mm; 
        //margin: 0mm;
        form.getToolbar().getTitleComponent().getAllStyles().setFgColor(0xffffff);
        form.getToolbar().getTitleComponent().getAllStyles().setAlignment(CENTER);
        form.getToolbar().getTitleComponent().getAllStyles().setFont(font);
        form.getToolbar().getTitleComponent().getAllStyles().setPadding(3, 0, 3, 0);

        form.setBackCommand(new Command("btnBack") {
            @Override
            public void actionPerformed(ActionEvent ev) {
                prevForm.showBack();
            }
        });
        Command backCmd = Command.create("",
                materialIcon(FontImage.MATERIAL_ARROW_BACK),
                evt -> {
                    prevForm.showBack();
                });

        form.getToolbar().addCommandToLeftBar(backCmd);
        return form;
    }

    protected static FontImage materialIcon(char charCode) {
        int size;
        if (Display.getInstance().isTablet()) {
            size = 8;
        } else {
            size = 4;
        }
        Font font = Font.createTrueTypeFont("native:MainLight",
                "native:MainLight").derive(Display.getInstance()
                        .convertToPixels(size), Font.STYLE_PLAIN);

        Style s = new Style(0xffffff, 0x3399ff, font, (byte) 0);
        FontImage fontImg = FontImage.createMaterial(charCode, s);

        fontImg.setPadding(0);
        //fontImg.setBgTransparency(255);
        return fontImg;
    }

    /**
     * @param formAnim - Form in which progress animation is added
     * @param size - progress bar size
     * @param width - progress bar width
     * @param bgColor - animation background color
     * @param progressColor - progress bar color
     * @param direction - progress direction (C - clockwise, A - anticlockwise)
     * @param duration - animation duration to determine speed(the lower the
     * duration the faster the progress speed)
     */
    public static void circleProgress(Form formAnim, int size, int width,
            int bgColor, int progressColor, String direction, int duration) {
        formAnim.setLayout(new BorderLayout());
        formAnim.getToolbar().hideToolbar();

        Form formProgress = new Form(new BorderLayout());
        formProgress.getToolbar().hideToolbar();
        //formParAnim3.setUIID("formProgressBar");

        formAnim.add(CENTER,
                FlowLayout.encloseCenterMiddle(createCircleProgress(formAnim,
                        formProgress, size, width, bgColor, progressColor,
                        direction, duration)));

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

        //gr.fillArc(0, 0, 200, 200, 0, currentProgress);
        if (direction.equals("A")) {
            //gr.fillArc(0, 0, 80, 80, 120, currentProgress);
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
            //gr.fillArc(0, 0, 80, 80, 60, currentProgress);
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
                    Log.p("ZT A Cycle=" + cycle);
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
                    Log.p("ZT C Cycle=" + cycle);
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

            circleProgress(formAnim, size, width, bgColor, progressColor, direction, duration);

        }).schedule(duration, false, formProgress);

        return formProgress;
    }

}
