package org.queelt.api.resource.v1.user;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AbstractUser  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "User id")
    private String userId;
    @ApiModelProperty(value = "User insert date")
    private Date insertDate;
    @ApiModelProperty(value = "Full name")
    private String name;
    @ApiModelProperty(value = "User email")
    private String email;
    @ApiModelProperty(value = "User phone")
    private String phone;
    @ApiModelProperty(value = "User birth date")
    private Date birthDate;
    @ApiModelProperty(value = "User gender")
    private Integer gender;
    @ApiModelProperty(value = "User password")
    private String password;
    
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public final boolean equals(final Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public final String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
