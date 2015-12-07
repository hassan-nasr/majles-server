<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
            <w:body>

                <xsl:for-each select="Ahadis/Hadis">

                    <w:tbl>
                        <w:tblPr>
                            <w:bidiVisual />
                            <w:tblW w:w="5000" w:type="pct" />
                            <w:tblLook w:val="04A0" />
                        </w:tblPr>
                        <w:tblGrid>
                            <w:gridCol w:w="599" />
                            <w:gridCol w:w="1908" />
                            <w:gridCol w:w="842" />
                            <w:gridCol w:w="993" />
                            <w:gridCol w:w="4386" />
                            <w:gridCol w:w="848" />
                        </w:tblGrid>
                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct" />
                                    <w:gridSpan w:val="6" />
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
                                        <w:t xml:space="preserve">كُد حديث: <xsl:value-of select="Hadiscode"/> ـ </w:t>
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
                                    <w:gridSpan w:val="6" />
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
                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="241" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="0050479D" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:tabs>
                                            <w:tab w:val="center" w:pos="2087" />
                                            <w:tab w:val="left" w:pos="3323" />
                                        </w:tabs>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t xml:space="preserve"> </w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="1011" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="0050479D" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:tabs>
                                            <w:tab w:val="center" w:pos="2087" />
                                            <w:tab w:val="left" w:pos="3323" />
                                        </w:tabs>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>متن حدیث</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="987" w:type="pct" />
                                    <w:gridSpan w:val="2" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="0050479D" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:tabs>
                                            <w:tab w:val="center" w:pos="2087" />
                                            <w:tab w:val="left" w:pos="3323" />
                                        </w:tabs>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>ترجمه</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="2304" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="0050479D" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:tabs>
                                            <w:tab w:val="center" w:pos="2087" />
                                            <w:tab w:val="left" w:pos="3323" />
                                        </w:tabs>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r w:rsidRPr="0050479D">
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>نکات</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="457" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:tabs>
                                            <w:tab w:val="center" w:pos="2087" />
                                            <w:tab w:val="left" w:pos="3323" />
                                        </w:tabs>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r w:rsidRPr="006671F1">
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>زمان تحقق</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:tr>

                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="241" w:type="pct" />
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
                                        <w:rPr>
                                            <w:b />
                                            <w:bCs />
                                            <w:rtl />
                                        </w:rPr>
                                    </w:pPr>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="1011" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:pStyle w:val="HeaderArabic" />
                                    </w:pPr>
                                    <w:r w:rsidRPr="00541529">
                                        <w:rPr>
                                            <w:rFonts w:hint="cs" />
                                            <w:rtl />
                                        </w:rPr>
                                        <w:t xml:space="preserve"><xsl:value-of select="Header2" /></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="987" w:type="pct" />
                                    <w:gridSpan w:val="2" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:pStyle w:val="HeaderFarsi" />
                                    </w:pPr>
                                    <w:r w:rsidRPr="00541529">
                                        <w:rPr>
                                            <w:rFonts w:hint="cs" />
                                            <w:rtl />
                                        </w:rPr>
                                        <w:t xml:space="preserve"><xsl:value-of select="HeaderFarsi2" /></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="2304" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="006671F1" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:pStyle w:val="EmptyLine" />
                                        <w:rPr>
                                            <w:rtl />
                                        </w:rPr>
                                    </w:pPr>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="457" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00FF5339" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:bidi />
                                        <w:spacing w:after="0" w:line="240" w:lineRule="auto" />
                                        <w:jc w:val="lowKashida" />
                                        <w:rPr>
                                            <w:rFonts w:ascii="B Lotus" w:eastAsia="Times New Roman" w:hAnsi="B Lotus" w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="00B050" />
                                            <w:sz w:val="18" />
                                            <w:szCs w:val="18" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                </w:p>
                            </w:tc>
                        </w:tr>

                        <xsl:for-each select="Bands/Band">


                            <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                                <w:tc>
                                    <w:tcPr>
                                        <w:tcW w:w="241" w:type="pct" />
                                        <w:tcBorders>
                                            <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        </w:tcBorders>
                                    </w:tcPr>
                                    <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                        <w:pPr>
                                            <w:pStyle w:val="BandNo" />
                                            <w:rPr>
                                                <w:rtl />
                                            </w:rPr>
                                        </w:pPr>
                                        <w:r w:rsidRPr="00541529">
                                            <w:rPr>
                                                <w:rFonts w:hint="cs" />
                                                <w:rtl />
                                            </w:rPr>
                                            <w:t><xsl:value-of select="NumberBand"/></w:t>
                                        </w:r>
                                    </w:p>
                                </w:tc>
                                <w:tc>
                                    <w:tcPr>
                                        <w:tcW w:w="1011" w:type="pct" />
                                        <w:tcBorders>
                                            <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        </w:tcBorders>
                                    </w:tcPr>
                                    <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                        <w:pPr>
                                            <w:pStyle w:val="Arabic" />
                                        </w:pPr>
                                        <w:r w:rsidRPr="00541529">
                                            <w:rPr>
                                                <w:rFonts w:hint="cs" />
                                                <w:rtl />
                                            </w:rPr>
                                            <w:t xml:space="preserve"><xsl:value-of select="ArabicBand" /></w:t>
                                        </w:r>
                                    </w:p>
                                </w:tc>
                                <w:tc>
                                    <w:tcPr>
                                        <w:tcW w:w="987" w:type="pct" />
                                        <w:gridSpan w:val="2" />
                                        <w:tcBorders>
                                            <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        </w:tcBorders>
                                    </w:tcPr>
                                    <w:p w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                        <w:pPr>
                                            <w:pStyle w:val="Farsi" />
                                        </w:pPr>
                                        <w:r w:rsidRPr="00541529">
                                            <w:rPr>
                                                <w:rFonts w:hint="cs" />
                                                <w:rtl />
                                            </w:rPr>
                                            <w:t xml:space="preserve"><xsl:value-of select="FarsiBand" /></w:t>
                                        </w:r>
                                    </w:p>
                                </w:tc>
                                <w:tc>
                                    <w:tcPr>
                                        <w:tcW w:w="2304" w:type="pct" />
                                        <w:tcBorders>
                                            <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        </w:tcBorders>
                                    </w:tcPr>
                                    <w:p w:rsidR="00D85B86" w:rsidRPr="006671F1" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                        <w:pPr>
                                            <w:pStyle w:val="EmptyLine" />
                                            <w:rPr>
                                                <w:rtl />
                                            </w:rPr>
                                        </w:pPr>
                                    </w:p>
                                </w:tc>
                                <w:tc>
                                    <w:tcPr>
                                        <w:tcW w:w="457" w:type="pct" />
                                        <w:tcBorders>
                                            <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                            <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        </w:tcBorders>
                                    </w:tcPr>
                                    <w:p w:rsidR="00D85B86" w:rsidRPr="00FF5339" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                        <w:pPr>
                                            <w:bidi />
                                            <w:spacing w:after="0" w:line="240" w:lineRule="auto" />
                                            <w:jc w:val="lowKashida" />
                                            <w:rPr>
                                                <w:rFonts w:ascii="B Lotus" w:eastAsia="Times New Roman" w:hAnsi="B Lotus" w:cs="B Lotus" />
                                                <w:b />
                                                <w:bCs />
                                                <w:color w:val="00B050" />
                                                <w:sz w:val="18" />
                                                <w:szCs w:val="18" />
                                                <w:rtl />
                                                <w:lang w:bidi="fa-IR" />
                                            </w:rPr>
                                        </w:pPr>
                                    </w:p>
                                </w:tc>
                            </w:tr>

                        </xsl:for-each>


                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="4543" w:type="pct" />
                                    <w:gridSpan w:val="5" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r w:rsidRPr="00BA500C">
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>جمع بندی روایت</w:t>
                                    </w:r>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>:</w:t>
                                    </w:r>
                                </w:p>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00A23C13" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="both" />
                                        <w:rPr>
                                            <w:rFonts w:cs="Times New Roman" />
                                            <w:b />
                                            <w:bCs />
                                            <w:i />
                                            <w:iCs />
                                            <w:color w:val="4F81BD" />
                                            <w:sz w:val="18" />
                                            <w:szCs w:val="18" />
                                            <w:u w:val="single" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="457" w:type="pct" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00BA500C" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                </w:p>
                            </w:tc>
                        </w:tr>
                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="1706" w:type="pct" />
                                    <w:gridSpan w:val="3" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00BA500C" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>زمان صدور</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="3294" w:type="pct" />
                                    <w:gridSpan w:val="3" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00BA500C" w:rsidRDefault="00E36CDC" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:tabs>
                                            <w:tab w:val="center" w:pos="2087" />
                                            <w:tab w:val="left" w:pos="3323" />
                                        </w:tabs>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r w:rsidR="00D85B86">
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" w:hint="cs" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="C00000" />
                                            <w:sz w:val="20" />
                                            <w:szCs w:val="20" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                        <w:t>خصوصیات مخاطب</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:tr>
                        <w:tr w:rsidR="00D85B86" w:rsidRPr="00541529" w:rsidTr="00310BDD">
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="1706" w:type="pct" />
                                    <w:gridSpan w:val="3" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRPr="00DF3AD9" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="4F81BD" />
                                            <w:sz w:val="18" />
                                            <w:szCs w:val="18" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
                                </w:p>
                            </w:tc>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="3294" w:type="pct" />
                                    <w:gridSpan w:val="3" />
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:left w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                        <w:right w:val="single" w:sz="4" w:space="0" w:color="D9D9D9" w:themeColor="background1" w:themeShade="D9" />
                                    </w:tcBorders>
                                </w:tcPr>
                                <w:p w:rsidR="00D85B86" w:rsidRDefault="00D85B86" w:rsidP="00981766">
                                    <w:pPr>
                                        <w:tabs>
                                            <w:tab w:val="center" w:pos="2087" />
                                            <w:tab w:val="left" w:pos="3323" />
                                        </w:tabs>
                                        <w:bidi />
                                        <w:spacing w:after="0" />
                                        <w:jc w:val="center" />
                                        <w:rPr>
                                            <w:rFonts w:cs="B Lotus" />
                                            <w:b />
                                            <w:bCs />
                                            <w:color w:val="4F81BD" />
                                            <w:sz w:val="18" />
                                            <w:szCs w:val="18" />
                                            <w:rtl />
                                            <w:lang w:bidi="fa-IR" />
                                        </w:rPr>
                                    </w:pPr>
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
