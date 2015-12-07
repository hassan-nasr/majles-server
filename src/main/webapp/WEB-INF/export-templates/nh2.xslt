<?xml version='1.0' encoding='utf-8'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <w:document
                xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                >
            <w:body>

                <xsl:for-each select="Paras/Para">

                    <w:p w:rsidR="00C3683D" w:rsidRPr="00541529" w:rsidRDefault="00C3683D" w:rsidP="00C3683D">
                        <w:pPr>
                            <w:bidi/>
                            <w:spacing w:after="480" />
                            <w:rPr>
                                <w:rtl/>
                            </w:rPr>
                        </w:pPr>

                        <xsl:for-each select="Runs/Run">
                            <w:r w:rsidRPr="0038130C">
                                <w:rPr>
                                    <w:rtl/>

                                    <w:rFonts w:cs="B Badr" w:hint="cs"/>
                                    <w:sz w:val="42"/>
                                    <w:szCs w:val="42"/>

                                    <xsl:if test="contains(Tags, 'beginning')">
                                        <w:rFonts w:cs="B Badr" w:hint="cs"/>
                                        <w:sz w:val="42"/>
                                        <w:szCs w:val="42"/>
                                        <w:u w:val="single"/>
                                    </xsl:if>
                                    <xsl:if test="contains(Tags, 'mainnarrator')">
                                        <w:rFonts w:cs="B Badr" w:hint="cs"/>
                                        <w:sz w:val="42"/>
                                        <w:szCs w:val="42"/>
                                        <w:b/>
                                        <w:bCs/>
                                        <w:color w:val="FF0000"/>
                                        <w:shd w:val="clear" w:color="auto" w:fill="92CDDC" w:themeFill="accent5"
                                               w:themeFillTint="99"/>
                                    </xsl:if>
                                    <xsl:if test="contains(Tags, 'othernarrator')">
                                        <w:rFonts w:cs="B Badr" w:hint="cs"/>
                                        <w:sz w:val="42"/>
                                        <w:szCs w:val="42"/>
                                        <w:i/>
                                        <w:iCs/>
                                        <w:shd w:val="clear" w:color="auto" w:fill="FFEEAA" w:themeFill="accent5"
                                               w:themeFillTint="99"/>
                                    </xsl:if>

                                    <xsl:if test="contains(Tags, 'aya')">
                                        <w:rFonts w:cs="B Badr" w:hint="cs"/>
                                        <w:sz w:val="42"/>
                                        <w:szCs w:val="42"/>
                                        <w:shd w:val="clear" w:color="auto" w:fill="FFEEEE" w:themeFill="accent5"
                                               w:themeFillTint="99"/>
                                    </xsl:if>

                                    <xsl:if test="contains(Tags, 'hemistich')">
                                        <w:i/>
                                        <w:iCs/>
                                    </xsl:if>


                                </w:rPr>
                                <w:t xml:space="preserve"><xsl:value-of select="Text"/></w:t>
                            </w:r>
                        </xsl:for-each>

                    </w:p>

                </xsl:for-each>

                <w:sectPr w:rsidR="00A324C6" w:rsidSect="00646832">
                    <w:pgSz w:w="15840" w:h="12240" w:orient="landscape"/>
                    <w:pgMar w:top="1440" w:right="1440" w:bottom="1440" w:left="1440" w:header="720" w:footer="720"
                             w:gutter="0"/>
                    <w:cols w:space="720"/>
                    <w:docGrid w:linePitch="360"/>
                </w:sectPr>
            </w:body>
        </w:document>
    </xsl:template>
</xsl:stylesheet>
