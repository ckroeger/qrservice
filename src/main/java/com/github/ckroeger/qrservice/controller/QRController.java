package com.github.ckroeger.qrservice.controller;

import com.github.ckroeger.qrservice.service.QRCodeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
@RestController
public class QRController {

    public static final String DEFAULT_SIZE = "350";
    private final QRCodeService qrCodeService;

    /**
     * Generates a QR-Code.
     *
     * Example Urls:
     * <ul>
     *     <li>http://localhost:8080/qrcode?text=Hallo</li>
     *     <li>http://localhost:8080/qrcode?text=Hallo&width=450&height=450</li>
     * </ul>
     * @param response {@link HttpServletResponse} injected by spring
     * @param text Text that is converted to qrcode
     * @param width in pixel (default = 350)
     * @param height in pixel (default = 350)
     * @throws Exception
     */
    @GetMapping("/qrcode")
    public void generateQRCode(HttpServletResponse response,
                               @RequestParam String text,
                               @RequestParam(defaultValue = DEFAULT_SIZE) int width,
                               @RequestParam(defaultValue = DEFAULT_SIZE) int height) throws Exception {
        BufferedImage image = qrCodeService.generateQRCode(text, width, height);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
