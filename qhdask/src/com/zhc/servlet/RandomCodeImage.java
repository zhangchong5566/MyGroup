package com.zhc.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class RandomCodeImage extends HttpServlet {
	
	public static final String RANDOMCODENAME = "randomCode";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);

		int width = this.getIntParamter(request, "width", 60);
		int height = this.getIntParamter(request, "height", 20);
		int fontSize = this.getIntParamter(request, "fontSize", 18);
		BufferedImage image = new BufferedImage(width, height, 1);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Times New Roman", 0, fontSize));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		String sRand = "";
		int a = random.nextInt(50);
		int b = random.nextInt(20);
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand = sRand + rand;

			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(rand, 13 * i + 6+a, 17+b);
		}
		request.getSession().setAttribute("randomCode", sRand);

		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception localException) {
		}
	}

	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public int getIntParamter(HttpServletRequest request, String _param, int _defaultValue) {
		int dv = _defaultValue;
		int rdv = 0;
		String strIn = request.getParameter(_param);
		if (StringUtils.isBlank(strIn)) {
			return dv;
		}
		try {
			rdv = Integer.parseInt(strIn);
		} catch (Exception e) {
			rdv = dv;
		}
		return rdv;
	}
}
