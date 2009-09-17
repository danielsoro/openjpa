/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package org.apache.openjpa.persistence.enhance.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MappedById;

import org.apache.openjpa.persistence.jdbc.VersionColumn;

/**
 * Entity used to test compound primary keys using entity as relationship to 
 * more than one level.
 * 
 * Test case and domain classes were originally part of the reported issue
 * <A href="https://issues.apache.org/jira/browse/OPENJPA-207">OPENJPA-207</A>
 *  
 * @author Jeffrey Blattman
 * @author Pinaki Poddar
 *
 */
@Entity

@VersionColumn
public class Page1 implements Serializable {
    @EmbeddedId
    private PageId1 pid;

    @MappedById("book")
    @Column(nullable = false)
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="BOOK_LIBRARY_LIBRARY_NAME",
                referencedColumnName="LIBRARY_LIBRARY_NAME"),
        @JoinColumn(name="BOOK_BOOK_NAME", referencedColumnName="BOOK_NAME")    
    })
    private Book1 book;
    
    public PageId1 getPid() {
        return pid;
    }

    public void setPid(PageId1 pid) {
        this.pid = pid;
    }

    public Book1 getBook() {
        return book;
    }

    public void setBook(Book1 book) {
        this.book = book;
    }    
}