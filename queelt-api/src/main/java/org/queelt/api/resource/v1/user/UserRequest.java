package org.queelt.api.resource.v1.user;

import javax.xml.bind.annotation.XmlRootElement;

import com.wordnik.swagger.annotations.Api;

@XmlRootElement
@Api("User request")
public final class UserRequest extends AbstractUser {
	private static final long serialVersionUID = 1L;
}
