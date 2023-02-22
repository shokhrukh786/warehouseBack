package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AttachmentContent extends AbstractEntity {

    @Column(nullable = false)
    private byte[] bytes;

    @JoinColumn(nullable = false)
    @OneToOne
    private Attachment attachment;
}
