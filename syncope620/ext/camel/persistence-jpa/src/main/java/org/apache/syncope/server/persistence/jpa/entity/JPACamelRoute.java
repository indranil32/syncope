/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.server.persistence.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.syncope.common.lib.types.SubjectType;
import org.apache.syncope.server.persistence.api.entity.CamelRoute;

@Entity
@Table(name = JPACamelRoute.TABLE)
public class JPACamelRoute extends AbstractEntity<String> implements CamelRoute {

    private static final long serialVersionUID = -2767606675667839161L;

    public static final String TABLE = "CamelRoute";

    @Id
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SubjectType subject;

    @Lob
    private String content;

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public void setKey(final String name) {
        this.name = name;
    }

    @Override
    public SubjectType getSubjectType() {
        return subject;
    }

    @Override
    public void setSubjectType(final SubjectType subject) {
        this.subject = subject;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(final String content) {
        this.content = content;
    }

}
