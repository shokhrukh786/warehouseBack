package uz.shohruh.omborxonabackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
    Optional<AttachmentContent> findByAttachmentId(Long id);
}
