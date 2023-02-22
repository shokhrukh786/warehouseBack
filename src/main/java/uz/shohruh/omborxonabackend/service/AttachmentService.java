package uz.shohruh.omborxonabackend.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.shohruh.omborxonabackend.entity.Attachment;
import uz.shohruh.omborxonabackend.entity.AttachmentContent;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.repository.AttachmentContentRepository;
import uz.shohruh.omborxonabackend.repository.AttachmentRepository;


import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    @SneakyThrows
    public ApiResponse addAttachment(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        if (file != null) {
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment save = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(save);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
            return new ApiResponse("Successfully Attachment",true ,attachment.getId());
        }
        return new ApiResponse("file not uploaded",false);
    }

    @SneakyThrows
    public ApiResponse getFile(Long id, HttpServletResponse response) {
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()) {
            Attachment attachment = byId.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();
                response.setHeader("Content-Disposition", "" +
                        "attachment;filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
                return new ApiResponse("download successfully",true,attachment.getId());
            }
        }
        return new ApiResponse("not found",false);
    }

    @SneakyThrows
    public ApiResponse editUpload(Long id, MultipartHttpServletRequest request){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new ApiResponse("file not uploaded",true);
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = optionalAttachment.get();
        if (file != null) {
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment save = attachmentRepository.save(attachment);
            Optional<AttachmentContent> content = attachmentContentRepository.findByAttachmentId(id);
            if (!content.isPresent())
                return new ApiResponse("File not Content",false);
            AttachmentContent attachmentContent = content.get();
            attachmentContent.setAttachment(save);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
            return new ApiResponse("Successfully edited file",true,attachment.getId());
        }
        return new ApiResponse("file not found",false);
    }

    public ApiResponse deletedAttachment(Long id){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()){
            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
            byAttachmentId.ifPresent(attachmentContent -> attachmentContentRepository.deleteById(attachmentContent.getId()));
            attachmentRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("There is no file for such an id",false);
    }

}
