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
import com.sorteio.core.models.Success;
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
    Success.class,
    ComponentExporter.class
}, resourceType = "sorteio/components/success")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class SuccessImpl
    implements Success
{

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String successTitle;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String successText;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String successBtn;
    @SlingObject
    private Resource resource;

    @Override
    @JsonProperty("successTitle")
    public String getSuccessTitle() {
        return successTitle;
    }

    @Override
    @JsonProperty("successText")
    public String getSuccessText() {
        return successText;
    }

    @Override
    @JsonProperty("successBtn")
    public String getSuccessBtn() {
        return successBtn;
    }

    @Override
    public String getExportedType() {
        return resource.getResourceType();
    }

}
