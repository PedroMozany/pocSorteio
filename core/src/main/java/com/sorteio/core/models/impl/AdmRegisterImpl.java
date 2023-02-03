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
import com.sorteio.core.models.AdmRegister;
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
    AdmRegister.class,
    ComponentExporter.class
}, resourceType = "sorteio/components/adm-register")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class AdmRegisterImpl
    implements AdmRegister
{

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String titleText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String userPlaceholderText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String userLabelText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String placeholderPasswordText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String labelPasswordText;
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
    @JsonProperty("userPlaceholderText")
    public String getUserPlaceholderText() {
        return userPlaceholderText;
    }

    @Override
    @JsonProperty("userLabelText")
    public String getUserLabelText() {
        return userLabelText;
    }

    @Override
    @JsonProperty("placeholderPasswordText")
    public String getPlaceholderPasswordText() {
        return placeholderPasswordText;
    }

    @Override
    @JsonProperty("labelPasswordText")
    public String getLabelPasswordText() {
        return labelPasswordText;
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
