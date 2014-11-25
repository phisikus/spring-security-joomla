package pl.weimaraner.klub.baza.joomla.utils;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * This class is mainly responsible for adding prefix to names of tables used by hibernate in this library.
 *
 */
public class JoomlaTableNamingStrategy extends DefaultNamingStrategy {
    private static final long serialVersionUID = 1L;

    /**
     * This value should be the same as $dbprefix defined in configuration.php in Joomla main directory.
     */
    private static String tablePrefix;

    public JoomlaTableNamingStrategy(final String databaseTablePrefix) {
        tablePrefix = databaseTablePrefix;
    }

    private String addPrefix(final String tableName) {
        return tablePrefix + tableName;
    }

    @Override
    public String classToTableName(final String className) {
        return this.addPrefix(super.classToTableName(className).toLowerCase());
    }

    @Override
    public String tableName(String tableName) {
        return addPrefix(super.tableName(tableName).toLowerCase());
    }

    @Override
    public String collectionTableName(final String ownerEntity,
                                      final String ownerEntityTable, final String associatedEntity,
                                      final String associatedEntityTable, final String propertyName) {
        return this.addPrefix(super.collectionTableName(ownerEntity,
                ownerEntityTable, associatedEntity, associatedEntityTable,
                propertyName).toLowerCase());
    }

    @Override
    public String logicalCollectionTableName(final String tableName,
                                             final String ownerEntityTable, final String associatedEntityTable,
                                             final String propertyName) {
        return this.addPrefix(super.logicalCollectionTableName(tableName,
                ownerEntityTable, associatedEntityTable, propertyName).toLowerCase());
    }


}
