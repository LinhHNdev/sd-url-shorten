package se.linhhndev.sd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "url_info")
@Entity
@Data
public class URLInfo {

    @Id
    private String shortLink;

    @Column
    private String rawLink;
}
