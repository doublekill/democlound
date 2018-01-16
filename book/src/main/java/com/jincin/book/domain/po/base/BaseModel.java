package com.jincin.book.domain.po.base;

import com.jincin.book.util.annotation.InitNumber;
import com.jincin.book.util.annotation.CreateTime;
import com.jincin.book.util.annotation.LastUpdateTime;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

/******************************************
 * Created by dongwujing on 2017/7/3.
 *
 ******************************************/
@MappedSuperclass
public class BaseModel {

    @ApiModelProperty(hidden = true)
    @Version
    private Long version;

    @ApiModelProperty(value = "创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreateTime
    private Date dateCreated;

    @ApiModelProperty(value = "更新时间",hidden = true)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastUpdateTime
    private Date lastUpdated;

    @ApiModelProperty(value = "是否删除 0否,1是",hidden = true)
    @InitNumber
    private Integer isDeleted;


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
