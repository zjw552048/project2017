package com.z.plugin.kaptcha;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.impl.WaterRipple;
import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.WaterFilter;

/**
 * @author Administrator
 *
 */
public class MyRipple extends WaterRipple {

	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.impl.WaterRipple#getDistortedImage(java.awt.image.BufferedImage)
	 */
	@Override
	public BufferedImage getDistortedImage(BufferedImage baseImage) {
		NoiseProducer noiseProducer = getConfig().getNoiseImpl();
        BufferedImage distortedImage = new BufferedImage(baseImage.getWidth(), baseImage.getHeight(), 2);
        Graphics2D graphics = (Graphics2D)distortedImage.getGraphics();
        RippleFilter rippleFilter = new RippleFilter();
        rippleFilter.setWaveType(0);
        rippleFilter.setXAmplitude(1F);
        rippleFilter.setYAmplitude(1F);
        rippleFilter.setXWavelength(15F);
        rippleFilter.setYWavelength(5F);
        rippleFilter.setEdgeAction(0);
        WaterFilter waterFilter = new WaterFilter();
        waterFilter.setAmplitude(1.5F);
        waterFilter.setPhase(10F);
        waterFilter.setWavelength(1.0F);
        BufferedImage effectImage = waterFilter.filter(baseImage, null);
        effectImage = rippleFilter.filter(effectImage, null);
        graphics.drawImage(effectImage, 0, 0, null, null);
        graphics.dispose();
        noiseProducer.makeNoise(distortedImage, 0.1F, 0.1F, 0.25F, 0.25F);
        noiseProducer.makeNoise(distortedImage, 0.1F, 0.25F, 0.5F, 0.9F);
        return distortedImage;
	}

}
