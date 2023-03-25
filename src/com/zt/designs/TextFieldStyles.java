/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zt.designs;

import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author Eric.Chomba
 */
public class TextFieldStyles {

    public void styleTfCustom(Style style, int textColor) {
        /*tfCustom { font-size: 3mm; background: transparent; padding: 0mm; margin-left: 0mm; }
        @media device-tablet, density-high { tfCustom { font-size: 5.6mm; } }
        @media device-desktop, density-medium { tfCustom { font-size: 3.9mm; } }*/
        
        float size;
        if (Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() >= 40) {
            size = 5.6f;
        } else if (Display.getInstance().isDesktop() && Display.getInstance().getDeviceDensity() < 40) {
            size = 3.9f;
        } else {
            size = 3f;
        }

        Font font = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular")
                .derive(Display.getInstance().convertToPixels(size), Font.STYLE_PLAIN);
        style.setFont(font);
        style.setMargin(0, 0, 0, 0);
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(0, 0, 0, 0);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setFgColor(textColor);
        //style.setBgColor(0xffffff);
        style.setBgTransparency(0);
        style.setBorder(null);
    }

    public void styleTfBorder(Style style, int tfBorderColor, float tfBorderWidth) {
        /*cntTfBorder { background: blue; padding: 0.1mm; margin: 0mm 0mm 1mm 0mm; }*/

        style.setMargin(0, 1, 0, 0); //top bottom left right
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(tfBorderWidth, tfBorderWidth, tfBorderWidth, tfBorderWidth);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setBgColor(tfBorderColor);
        style.setBgTransparency(255);
    }

    public void styleTfBtn(Style style) {
        /*btnTf { background: transparent; padding: 0mm; }*/
        
        style.setPadding(0, 0, 0, 0);
        style.setBgColor(0);
    }

    public void styleLblHint(Style style, int hintColor) {
        /* lblHint { margin: 0mm; color: blue; font-size: 2mm; background: transparent; padding: 0mm; }
        @media device-tablet, density-high { lblHint {  font-size: 4mm;  } }
        @media device-desktop, density-medium { lblHint { font-size: 2.3mm;  } }*/
        
        float size;
        if (Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() >= 40) {
            size = 4f;
        } else if (Display.getInstance().isDesktop() && Display.getInstance().getDeviceDensity() < 40) {
            size = 2.3f;
        } else {
            size = 2f;
        }

        Font font = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular")
                .derive(Display.getInstance().convertToPixels(size), Font.STYLE_PLAIN);

        style.setFont(font);
        style.setMargin(0, 0, 0, 0);
        style.setPadding(0, 0, 0, 0);
        style.setFgColor(hintColor);
        style.setBgColor(0);
    }

    public void styleTfHint(Style style, int hintColor) {
        /*tfHint { margin: 0mm; color: blue; font-size: 3mm;  background: transparent; padding: 0mm; }
        @media device-tablet, density-high {  tfHint { font-size: 5.6mm;  }}
        @media device-desktop, density-medium { tfHint {  font-size: 3.9mm; }} */

        float size;
        if (Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() >= 40) {
            size = 5.6f;
        } else if (Display.getInstance().isDesktop() && Display.getInstance().getDeviceDensity() < 40) {
            size = 3.9f;
        } else {
            size = 3f;
        }

        Font font = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular")
                .derive(Display.getInstance().convertToPixels(size), Font.STYLE_PLAIN);
        style.setFont(font);
        style.setMargin(0, 0, 0, 0);
        style.setPadding(0, 0, 0, 0);
        style.setFgColor(hintColor);
        style.setBgColor(0);

    }

    public void styleCntBoxTfPar(Style style, int tfBgColor) {
        /*cntBoxTfPar { padding: 0mm;  margin: 0.5mm 0mm 0.5mm 0mm; background: white; }*/

        style.setMargin(0.5f, 0.5f, 0, 0); //top bottom left right
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(0, 0, 0, 0);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setBgColor(tfBgColor);
        style.setBgTransparency(255);
    }

    public void styleCntBoxTf(Style style, int tfBorderColor) {
        /*cntBoxTf { margin: 1mm 0mm 0mm 0mm; background: black; padding: 0mm; }*/

        style.setMargin(1f, 0, 0, 0);
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(0, 0, 0, 0);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setBgColor(tfBorderColor);
        style.setBgTransparency(255);
    }

    public void styleCntBoxTfBg(Style style, int tfBgColor, float tfBorderWidth) {
        /*cntBoxTfBg { margin: 0.1mm;  padding: 0mm; background: white; }*/

        style.setMargin(tfBorderWidth, tfBorderWidth, tfBorderWidth, tfBorderWidth);
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(0, 0, 0, 0);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setBgColor(tfBgColor);
        style.setBgTransparency(255);
    }

    public void styleTfBoxCustom(Style style, int bgColor, int textColor) {
        /*tfBoxCustom { 
            font-size: 3mm; padding: 2mm 0mm 2mm 0mm; margin: 0mm 0mm 0mm 2.5mm; sbackground: white; 
        }
        @media device-tablet, density-high { tfBoxCustom {  font-size: 5.6mm;  } }
        @media device-desktop, density-medium { tfBoxCustom { font-size: 3.9mm; } } */

        float size, padding;
        if (Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() >= 40) {
            size = 5.6f;
            padding = 3.5f;
        } else if (Display.getInstance().isDesktop() && Display.getInstance().getDeviceDensity() < 40) {
            size = 3.9f;
            padding = 2f;
        } else {
            size = 3f;
            padding = 2f;
        }

        Font font = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular")
                .derive(Display.getInstance().convertToPixels(size), Font.STYLE_PLAIN);
        style.setFont(font);
        style.setMargin(0, 0, 2.5f, 0f); //top bottom left right
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(padding, padding, 0, 0);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setFgColor(textColor);
        style.setBgColor(bgColor);
        //style.setBgTransparency(0);
        style.setBorder(null);
    }

    public void styleTfBoxHint(Style style, int hintColor) {
        /*tfBoxHint {  font-size: 3mm;  margin: 0mm 0mm 0mm 2.5mm; padding: 0mm; }
        @media device-tablet, density-high { tfBoxHint {  font-size: 5.6mm; } }
        @media device-desktop, density-medium { tfBoxHint { font-size: 3.9mm;  } } */

        float size;
        if (Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() >= 40) {
            size = 5.6f;
        } else if (Display.getInstance().isDesktop() && Display.getInstance().getDeviceDensity() < 40) {
            size = 3.9f;
        } else {
            size = 3f;
        }
        Font font = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular")
                .derive(Display.getInstance().convertToPixels(size), Font.STYLE_PLAIN);
        style.setFont(font);
        style.setMargin(0, 0, 2.5f, 0);
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(0, 0, 0, 0);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setFgColor(hintColor);
        style.setBgColor(0);

    }

    public void styleTfBoxBtn(Style style) {
        /*btnBoxTf { background: transparent; padding: 0mm; margin: 0.5mm; }*/
        
        style.setMargin(0.5f, 0.5f, 0.5f, 0.5f);
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(0, 0, 0, 0);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setBgColor(0);
    }

    public void styleLblBoxHint(Style style, int tfBgColor, int hintColor) {
        /*
        lblBoxHint { margin: 0mm 0mm 0mm 3mm;  font-size: 2mm; padding:0mm 0.5mm 0mm 0mm; 
        background: white;}
        @media device-tablet, density-high { lblBoxHint { font-size: 4mm; } }
        @media device-desktop, density-medium { lblBoxHint { font-size: 2.9mm; } }*/
        
        float size;
        if (Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() >= 40) {
            size = 4f;
        } else if (Display.getInstance().isDesktop() && Display.getInstance().getDeviceDensity() < 40) {
            size = 2.9f;
        } else {
            size = 2f;
        }

        Font font = Font.createTrueTypeFont("native:MainRegular", "native:MainRegular")
                .derive(Display.getInstance().convertToPixels(size), Font.STYLE_PLAIN);

        style.setFont(font);
        style.setMargin(0, 0, 3f, 0); //top bottom left right
        style.setMarginUnit(Style.UNIT_TYPE_DIPS);
        style.setPadding(0, 0, 0, 0.5f);
        style.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        style.setBgColor(tfBgColor);
        style.setBgTransparency(255);
        style.setFgColor(hintColor);

    }
}
