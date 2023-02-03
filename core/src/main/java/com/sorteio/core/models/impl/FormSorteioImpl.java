/*
 * ***********************************************************************
 * Page Sorteio CONFIDENTIAL
 * ___________________
 *
 * Copyright 2023 Page Sorteio.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property
 * of Page Sorteio and its suppliers, if any. The intellectual and
 * technical concepts contained herein are proprietary to Page Sorteio
 * and its suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Page Sorteio.
 * ***********************************************************************
 */

package com.sorteio.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sorteio.core.models.FormSorteio;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {
    SlingHttpServletRequest.class
}, adapters = {
    FormSorteio.class,
    ComponentExporter.class
}, resourceType = "sorteio/components/form-sorteio")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class FormSorteioImpl
    implements FormSorteio
{

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String titleText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String namePlaceholderText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String nameLabelText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String placeholderEmailText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String labelEmailText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String buttonText;
    @SlingObject
    private Resource resource;

    @Override
    @JsonProperty("titleText")
    public String getTitleText() {
        return titleText;
    }

    @Override
    @JsonProperty("namePlaceholderText")
    public String getNamePlaceholderText() {
        return namePlaceholderText;
    }

    @Override
    @JsonProperty("nameLabelText")
    public String getNameLabelText() {
        return nameLabelText;
    }

    @Override
    @JsonProperty("placeholderEmailText")
    public String getPlaceholderEmailText() {
        return placeholderEmailText;
    }

    @Override
    @JsonProperty("labelEmailText")
    public String getLabelEmailText() {
        return labelEmailText;
    }

    @Override
    @JsonProperty("buttonText")
    public String getButtonText() {
        return buttonText;
    }

    @Override
    public String getExportedType() {
        return resource.getResourceType();
    }

}
