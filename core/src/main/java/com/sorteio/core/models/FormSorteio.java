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

package com.sorteio.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.osgi.annotation.versioning.ConsumerType;


/**
 * Defines the {@code FormSorteio} Sling Model used for the {@code sorteio/components/form-sorteio} component.
 * 
 */
@ConsumerType
public interface FormSorteio
    extends ComponentExporter
{


    @JsonProperty("titleText")
    String getTitleText();

    @JsonProperty("namePlaceholderText")
    String getNamePlaceholderText();

    @JsonProperty("nameLabelText")
    String getNameLabelText();

    @JsonProperty("placeholderEmailText")
    String getPlaceholderEmailText();

    @JsonProperty("labelEmailText")
    String getLabelEmailText();

    @JsonProperty("buttonText")
    String getButtonText();

}
