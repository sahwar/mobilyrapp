/*
 *
 * Copyright (c) 2007, Sun Microsystems, Inc.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * Neither the name of Sun Microsystems nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package util;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.m2g.SVGImage;
import javax.microedition.m2g.ScalableGraphics;
import org.w3c.dom.svg.SVGPoint;
import org.w3c.dom.svg.SVGSVGElement;

public class SVGImageCanvas extends Canvas {

    /**
     * The SVGImage painted by the canvas.
     */
    protected SVGImage svgImage;
    protected int xOffset = 0;
    /**
     * The ScalableGraphics used to paint into the midp
     * Graphics instance.
     */
    protected ScalableGraphics sg = ScalableGraphics.createInstance();

    /**
     * @param svgImage the SVGImage this canvas should paint.
     */
    public SVGImageCanvas(final SVGImage svgImage) {
        this.svgImage = svgImage;
    }

    public void setMoveXOffest(int xOffset) {
        this.xOffset = this.xOffset + xOffset;

        SVGSVGElement myEl = (SVGSVGElement) svgImage.getDocument().getDocumentElement();
	    SVGPoint origin = myEl.getCurrentTranslate();
	    origin.setX(this.xOffset);
	    origin.setY(0);
	    repaint();

    }

    public void keyPressed(int key) {
        if (key == getKeyCode(Canvas.LEFT)) {
            setMoveXOffest(+30);
        } else if (key == getKeyCode(Canvas.RIGHT)) {
            setMoveXOffest(-30);
        }

        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        sg.bindTarget(g);

        svgImage.setViewportWidth(30 * 24 + 100);
        svgImage.setViewportHeight(getHeight());

        sg.render(0, 0, svgImage);

        sg.releaseTarget();
    }
}
