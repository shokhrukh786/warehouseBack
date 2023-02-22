package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.service.AttachmentService;


import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
        @Autowired
        private final AttachmentService attachmentService;


        public AttachmentController(AttachmentService attachmentService) {
            this.attachmentService = attachmentService;
        }

        @PostMapping("/upload")
        public ApiResponse addFile(MultipartHttpServletRequest request){
            return attachmentService.addAttachment(request);
        }

        @GetMapping("/download/{id}")
        public ApiResponse getFile(@PathVariable Long id, HttpServletResponse response){
            return attachmentService.getFile(id, response);
        }

        @PutMapping("/{id}")
        public ApiResponse editFile(@PathVariable Long id,MultipartHttpServletRequest request){
            return attachmentService.editUpload(id, request);
        }

        @DeleteMapping("/{id}")
        public ApiResponse deletedFile(@PathVariable Long id){
            return attachmentService.deletedAttachment(id);
        }

}
