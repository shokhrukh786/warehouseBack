package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
