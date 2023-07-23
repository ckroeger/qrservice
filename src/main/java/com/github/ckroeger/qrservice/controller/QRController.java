package com.github.ckroeger.qrservice.controller;

import com.github.ckroeger.qrservice.service.QRCodeService;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("qr/")
public class QRController {

    public static final String DEFAULT_SIZE = "350";
    private final QRCodeService qrCodeService;

    /**
     * Generates a QR-Code.
     * <p>
     * Example Urls:
     * <ul>
     *     <li>http://localhost:8080/qr/qrcode?text=Hallo</li>
     *     <li>http://localhost:8080/qr/qrcode?text=Hallo&width=450&height=450</li>
     *     <li>http://localhost:8080/qr/qrcode.png?text=Hallo&asFile=true</li>
     * </ul>
     *
     * @param fileName path-param for name (optional)
     * @param text     Text that is converted to qrcode (required)
     * @param asFile   download as file (default = false)
     * @param width    in pixel (optional - default = 350)
     * @param height   in pixel (optional - default = 350)
     */
    @GetMapping(path = {"", "/{fileName}"})
    @Operation(summary = "Download a qrfile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {@Content(
                    mediaType = MediaType.IMAGE_PNG_VALUE)})
    })
    public ResponseEntity<Resource> generateQRCode(
            @PathVariable(required = false) String fileName,
            @RequestParam String text,
            @RequestParam(value = "asFile", required = false, defaultValue = "false") boolean asFile,
            @RequestParam(defaultValue = DEFAULT_SIZE) int width,
            @RequestParam(defaultValue = DEFAULT_SIZE) int height
    ) throws IOException,

            WriterException {
        BufferedImage image = qrCodeService.generateQRCode(text, width, height);
        HttpHeaders headers = new HttpHeaders();
        return sendAsFile(headers, asFile, image, fileName);
    }

    private ResponseEntity<Resource> sendAsFile(HttpHeaders headers, boolean asFile, BufferedImage image, String fileName) throws IOException {
        if (asFile) {
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        }
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(toByteArray(image, "png"));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(asFile ? MediaType.APPLICATION_OCTET_STREAM : MediaType.IMAGE_PNG)
                .body(resource);
    }

    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        return baos.toByteArray();
    }
}
