<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
            <w:body>

                <xsl:for-each select="Items/narratedHadith">



                    <w:tbl>
                        <w:tblPr>
                            <w:bidiVisual />
                            <w:tblW w:w="5000" w:type="pct" />
                            <w:tblLook w:val="04A0" />
                        </w:tblPr>
                        <w:tblGrid>
                            <w:gridCol w:w="13176" />
                        </w:tblGrid>
                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:bidi />
                                        <w:spacing w:after="0" w:line="240" w:lineRule="auto" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rtl />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r w:rsidRPr="00541529">
                                        <w:rPr>
                                            <w:rFonts w:cs="B Badr" w:hint="cs" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                        </w:rPr>
                                        <w:t xml:space="preserve">كُد حديث: <xsl:value-of select="id"/> ـ </w:t>
                                    </w:r>
                                    <w:r w:rsidRPr="00541529">
                                        <w:rPr>
                                            <w:rFonts w:cs="B Badr" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:szCs w:val="18" />
                                            <w:rtl />
                                        </w:rPr>
                                        <w:t xml:space="preserve">منبع : <xsl:value-of select="MainRef" /></w:t>
                                    </w:r>
                                    <w:r w:rsidRPr="00541529">
                                        <w:rPr>
                                            <w:rFonts w:cs="B Badr" w:hint="cs" />
                                            <w:szCs w:val="18" />
                                            <w:rtl />
                                        </w:rPr>
                                        <w:t xml:space="preserve"> [ <xsl:value-of select="Header1" /> ]</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:tr>
                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:pStyle w:val="Gooyande" />
                                    </w:pPr>
                                    <w:r w:rsidRPr="00541529">
                                        <w:rPr>
                                            <w:rFonts w:hint="cs" />
                                            <w:rtl />
                                        </w:rPr>
                                        <w:t xml:space="preserve"><xsl:value-of select="Emam" /></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:tr>
                        <w:tr w:rsidR="00C3683D" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00C3683D" w:rsidRPr="00541529" w:rsidRDefault="00C3683D" w:rsidP="00C3683D">
                                    <w:pPr>
                                        <w:pStyle w:val="Arabic" />
                                        <w:rPr>
                                            <w:rFonts w:hint="cs" />
                                            <w:rtl />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:hint="cs" />
                                            <w:rtl />
                                        </w:rPr>
                                        <w:t xml:space="preserve"><xsl:value-of select="baseText" /></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:tr>
                    </w:tbl>

                    <w:p w:rsidR="00A324C6" w:rsidRDefault="00A324C6" />

                </xsl:for-each>

                <w:sectPr w:rsidR="00A324C6" w:rsidSect="00646832">
                    <w:pgSz w:w="15840" w:h="12240" w:orient="landscape" />
                    <w:pgMar w:top="1440" w:right="1440" w:bottom="1440" w:left="1440" w:header="720" w:footer="720" w:gutter="0" />
                    <w:cols w:space="720" />
                    <w:docGrid w:linePitch="360" />
                </w:sectPr>
            </w:body>
        </w:document>
    </xsl:template>
</xsl:stylesheet>
