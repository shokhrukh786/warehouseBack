package uz.shohruh.omborxonabackend.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.shohruh.omborxonabackend.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractEntity {

    /**
     * bu 5 ta filt har qanaqa klassimda bo'ladi.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)  //yangilab bo'lmasin va
    @CreationTimestamp     //object yaratilgan vaqtini avtomat tizim vaqtini olib qo'yadi
    private Timestamp createdAt;

    @Column(nullable = false)  //bunda o'zgarib turadi shu uchun updatable = false qoyilmaydi
    @UpdateTimestamp     //object tahrirlangan vaqtini avtomat tizim vaqtini olib qo'yadi
    private Timestamp updatedAt;

    /**
     * amallarni qaysidir user qiladi(qo'shadi obyekt, role vahakazo)
     * @CreatedBy - yaratgan odamni securitydan olib contextholderni authentication nida kim
     * turgan bo'lsa ushani yozib qo'yadi(userni id sini)
     * @ManyToOne - bo'lishi sababi ko'plab odamlar bitta narsani tahrirlashi mumkun
     * fetch = FetchType.LAZY - qachon biz murojat qilsakgina databasedan olib keladi.
     * !!!bu filt obyekt bo'lganligi uchun @Column emas @JoinColumn ishlatib ketamiz.
     *      qachonki bizni tipimiz o'zimiz yaratgan tipimizda bo'lsa @JoimColumn ishlatamiz.
     */
    @JoinColumn(updatable = false)  //tahrirlanmasin degani.
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY) //qaysidir class bilan bog'layapmiz shu uchun bu anotatsiya
    private User createdBy;

    //    @JoinColumn(updatable = false) bunga qo'yilmaydi chunki tahrirlanishi mumkun
    @LastModifiedBy
    @ManyToOne
    private User updatedBy; //kim tahrirlagani
}
