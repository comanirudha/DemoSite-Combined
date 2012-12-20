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

package com.mycompany.domain.catalog;

import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.util.LocaleUtil;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.CategoryImpl;
import org.broadleafcommerce.i18n.domain.catalog.CategoryTranslation;
import org.broadleafcommerce.i18n.domain.catalog.I18NCategory;
import org.broadleafcommerce.i18n.domain.catalog.I18NCategoryImpl;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import java.util.Map;


/**
 * @author Andre Azzolini (apazzolini)
 */
@Entity
public class MyCategoryImpl extends CategoryImpl implements I18NCategory,Category {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3374057824309643535L;
    @Embedded
    protected I18NCategoryImpl i18nExtension=new I18NCategoryImpl();

    public Map<String, CategoryTranslation> getTranslations() {
        return i18nExtension.getTranslations();
    }

    public void setTranslations(Map<String, CategoryTranslation> translations) {
        i18nExtension.setTranslations(translations);
    }
    
    @Override
    public String getName() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale()) {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                CategoryTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getName() != null) {
                    return translation.getName();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                CategoryTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getName() != null) {
                    return translation.getName();
                }
            }
        }

        return name;
    }
    
    @Override
    public String getDescription() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale()) {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                CategoryTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getDescription() != null) {
                    return translation.getDescription();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                CategoryTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getDescription() != null) {
                    return translation.getDescription();
                }
            }
        }
        return description;
    }
    
}
