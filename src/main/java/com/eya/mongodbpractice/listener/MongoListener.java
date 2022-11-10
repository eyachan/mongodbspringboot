package com.eya.mongodbpractice.listener;



/*
* Mongo Repository Event Lifecycle
When performing change on an entity, there is a suite of events happening from the start to the end. They can be separated into two groups.

The Write Events
They are triggered when adding or updating a document in the collection like save, insert, insertList. They are listed following their trigger order:

onBeforeConvert: It’s called before the object is converted to a document by a MongoConverter.
onBeforeSave: It’s called before inserting or saving the document in the database.
onAfterSave: It’s called after inserting or saving the document in the database.
The Read Events
They are triggered for the methods that read documents in the collection like find, findAndRemove, findOne, and getCollection. They are listed following their trigger order:

onAfterLoad: It’s called after the document has been retrieved from the database.
onAfterConvert: after the document retrieved from the database was converted to an entity.
Following this, it is clear that the best place to set value for createdAt and updatedAt is when the event onBeforeConvert is triggered because it is impossible to edit the object after being converted to a Document.
* */

import com.eya.mongodbpractice.schema.BaseModel;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MongoListener extends AbstractMongoEventListener<BaseModel> {


    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseModel> event) {
        super.onBeforeConvert(event);

        Date dateNow = new Date();

        event.getSource().setCreatedAt(dateNow);
        event.getSource().setUpdatedAt(dateNow);
    }
}
