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
 * Defines the {@code FormLogin} Sling Model used for the {@code sorteio/components/form-login} component.
 * 
 */
@ConsumerType
public interface FormLogin
    extends ComponentExporter
{


    @JsonProperty("titleText")
    String getTitleText();

    @JsonProperty("userPlaceholderText")
    String getUserPlaceholderText();

    @JsonProperty("userLabelText")
    String getUserLabelText();

    @JsonProperty("placeholderPasswordText")
    String getPlaceholderPasswordText();

    @JsonProperty("labelPasswordText")
    String getLabelPasswordText();

    @JsonProperty("buttonText")
    String getButtonText();

}
