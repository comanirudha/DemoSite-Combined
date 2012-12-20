/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mycompany.domain.search;

import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.util.LocaleUtil;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.core.search.domain.SearchFacetImpl;

import com.broadleafcommerce.i18n.domain.search.I18NSearchFacet;
import com.broadleafcommerce.i18n.domain.search.I18NSearchFacetImpl;
import com.broadleafcommerce.i18n.domain.search.SearchFacetTranslation;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import java.util.Map;


/**
 * @author Andre Azzolini (apazzolini)
 */
@Entity
public class MySearchFacetImpl extends SearchFacetImpl implements I18NSearchFacet {
    
    @Embedded
    protected I18NSearchFacetImpl i18nExtension=new I18NSearchFacetImpl();

    public Map<String, SearchFacetTranslation> getTranslations() {
        return i18nExtension.getTranslations();
    }

    public void setTranslations(Map<String, SearchFacetTranslation> translations) {
        i18nExtension.setTranslations(translations);
    }
    
	public String getLabel() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale()) {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                SearchFacetTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getLabel() != null) {
                    return translation.getLabel();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                SearchFacetTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getLabel() != null) {
                    return translation.getLabel();
                }
            }
        }
        return label;
	}
    

}
