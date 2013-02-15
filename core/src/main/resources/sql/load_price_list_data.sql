--
-- The Archetype is configured with "hibernate.hbm2ddl.auto" value="create-drop" in "persistence.xml".
--
-- This will cause hibernate to populate the database when the application is started by processing the files that
-- were configured in the hibernate.hbm2ddl.import_files property.
--
-- This file is responsible for loading the the catalog data used in the Archetype.   Implementers can change this file
-- to load their initial catalog.
--

INSERT INTO BLC_SEARCH_FACET_RANGE (SEARCH_FACET_RANGE_ID, SEARCH_FACET_ID, PRICE_LIST_ID, MIN_VALUE, MAX_VALUE) VALUES (5, 3, 3, 0, 9);
INSERT INTO BLC_SEARCH_FACET_RANGE (SEARCH_FACET_RANGE_ID, SEARCH_FACET_ID, PRICE_LIST_ID, MIN_VALUE, MAX_VALUE) VALUES (6, 3, 3, 9, 17);
INSERT INTO BLC_SEARCH_FACET_RANGE (SEARCH_FACET_RANGE_ID, SEARCH_FACET_ID, PRICE_LIST_ID, MIN_VALUE, MAX_VALUE) VALUES (7, 3, 3, 17, null);


update  BLC_SEARCH_FACET_RANGE set  PRICE_LIST_ID=2 where price_list_id is null;