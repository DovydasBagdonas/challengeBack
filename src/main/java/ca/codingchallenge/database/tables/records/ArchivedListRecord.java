/*
 * This file is generated by jOOQ.
*/
package codingchallenge.database.tables.records;


import codingchallenge.database.tables.ArchivedList;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ArchivedListRecord extends UpdatableRecordImpl<ArchivedListRecord> implements Record4<Integer, String, String, String> {

    private static final long serialVersionUID = 102610403;

    /**
     * Setter for <code>public.archived_list.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.archived_list.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.archived_list.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.archived_list.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.archived_list.statement</code>.
     */
    public void setStatement(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.archived_list.statement</code>.
     */
    public String getStatement() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.archived_list.archive_date</code>.
     */
    public void setArchiveDate(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.archived_list.archive_date</code>.
     */
    public String getArchiveDate() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ArchivedList.ARCHIVED_LIST.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return ArchivedList.ARCHIVED_LIST.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return ArchivedList.ARCHIVED_LIST.STATEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return ArchivedList.ARCHIVED_LIST.ARCHIVE_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getArchiveDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getArchiveDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArchivedListRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArchivedListRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArchivedListRecord value3(String value) {
        setStatement(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArchivedListRecord value4(String value) {
        setArchiveDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArchivedListRecord values(Integer value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ArchivedListRecord
     */
    public ArchivedListRecord() {
        super(ArchivedList.ARCHIVED_LIST);
    }

    /**
     * Create a detached, initialised ArchivedListRecord
     */
    public ArchivedListRecord(Integer id, String name, String statement, String archiveDate) {
        super(ArchivedList.ARCHIVED_LIST);

        set(0, id);
        set(1, name);
        set(2, statement);
        set(3, archiveDate);
    }
}
