package com.github.ckroeger.qrservice.controller;

import com.github.ckroeger.qrservice.service.QRCodeService;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class QRController {

    public static final String DEFAULT_SIZE = "350";
    private final QRCodeService qrCodeService;

    /**
     * Generates a QR-Code.
     * <p>
     * Example Urls:
     * <ul>
     *     <li>http://localhost:8080/qrcode?text=Hallo</li>
     *     <li>http://localhost:8080/qrcode?text=Hallo&width=450&height=450</li>
     * </ul>
     *
     * @param response {@link HttpServletResponse} injected by spring
     * @param text     Text that is converted to qrcode
     * @param width    in pixel (default = 350)
     * @param height   in pixel (default = 350)
     * @throws Exception
     */
    @GetMapping(
            path = "/qrcode.png",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {@Content(
                    mediaType = MediaType.IMAGE_PNG_VALUE)})
    })
    public void generateQRCode(HttpServletResponse response,
                               @RequestParam String text,
                               @RequestParam(defaultValue = DEFAULT_SIZE) int width,
                               @RequestParam(defaultValue = DEFAULT_SIZE) int height) throws WriterException, IOException {
        BufferedImage image = qrCodeService.generateQRCode(text, width, height);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(path = "/qrcode/{fileName}", method = RequestMethod.GET)
    @Operation(summary = "Download a qrfile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {@Content(
                    mediaType = MediaType.IMAGE_PNG_VALUE)})
    })
    public ResponseEntity<Resource> downloadQrFile(
            @PathVariable String fileName,
            @RequestParam String text,
            @RequestParam(defaultValue = DEFAULT_SIZE) int width,
            @RequestParam(defaultValue = DEFAULT_SIZE) int height
    ) throws IOException, WriterException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        BufferedImage image = qrCodeService.generateQRCode(text, width, height);
        ByteArrayResource resource = new ByteArrayResource(toByteArray(image, "png"));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        return baos.toByteArray();
    }
}
