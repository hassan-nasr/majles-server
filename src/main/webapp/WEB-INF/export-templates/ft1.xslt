<?xml version='1.0' encoding='utf-8'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:param name="HadithTranslationItalic"/>
    <xsl:param name="HadithAyaBackcolor"/>
    <xsl:param name="HadithAyaBold"/>
    <xsl:param name="HadithAyaFontname"/>
    <xsl:param name="HadithAyaFontsize"/>
    <xsl:param name="HadithAyaForecolor"/>
    <xsl:param name="HadithAyaItalic"/>
    <xsl:param name="HadithAyaUnderline"/>
    <xsl:param name="HadithBeginningBackcolor"/>
    <xsl:param name="HadithBeginningBold"/>
    <xsl:param name="HadithBeginningFontname"/>
    <xsl:param name="HadithBeginningFontsize"/>
    <xsl:param name="HadithBeginningForecolor"/>
    <xsl:param name="HadithBeginningItalic"/>
    <xsl:param name="HadithBeginningUnderline"/>
    <xsl:param name="HadithCodeBackcolor"/>
    <xsl:param name="HadithCodeBold"/>
    <xsl:param name="HadithCodeFontname"/>
    <xsl:param name="HadithCodeFontsize"/>
    <xsl:param name="HadithCodeForecolor"/>
    <xsl:param name="HadithCodeItalic"/>
    <xsl:param name="HadithCodeUnderline"/>
    <xsl:param name="HadithGlossaryBackcolor"/>
    <xsl:param name="HadithGlossaryBold"/>
    <xsl:param name="HadithGlossaryFontname"/>
    <xsl:param name="HadithGlossaryFontsize"/>
    <xsl:param name="HadithGlossaryForecolor"/>
    <xsl:param name="HadithGlossaryItalic"/>
    <xsl:param name="HadithGlossaryUnderline"/>
    <xsl:param name="HadithHemistichBackcolor"/>
    <xsl:param name="HadithHemistichBold"/>
    <xsl:param name="HadithHemistichFontname"/>
    <xsl:param name="HadithHemistichFontsize"/>
    <xsl:param name="HadithHemistichForecolor"/>
    <xsl:param name="HadithHemistichItalic"/>
    <xsl:param name="HadithHemistichUnderline"/>
    <xsl:param name="HadithMainNarratorBackcolor"/>
    <xsl:param name="HadithMainNarratorBold"/>
    <xsl:param name="HadithMainNarratorFontname"/>
    <xsl:param name="HadithMainNarratorFontsize"/>
    <xsl:param name="HadithMainNarratorForecolor"/>
    <xsl:param name="HadithMainNarratorItalic"/>
    <xsl:param name="HadithMainNarratorUnderline"/>
    <xsl:param name="HadithOtherNarratorBackcolor"/>
    <xsl:param name="HadithOtherNarratorBold"/>
    <xsl:param name="HadithOtherNarratorFontname"/>
    <xsl:param name="HadithOtherNarratorFontsize"/>
    <xsl:param name="HadithOtherNarratorForecolor"/>
    <xsl:param name="HadithOtherNarratorItalic"/>
    <xsl:param name="HadithOtherNarratorUnderline"/>
    <xsl:param name="HadithRefBackcolor"/>
    <xsl:param name="HadithRefBold"/>
    <xsl:param name="HadithRefFontname"/>
    <xsl:param name="HadithRefFontsize"/>
    <xsl:param name="HadithRefForecolor"/>
    <xsl:param name="HadithRefItalic"/>
    <xsl:param name="HadithRefUnderline"/>
    <xsl:param name="HadithTextBackcolor"/>
    <xsl:param name="HadithTextBold"/>
    <xsl:param name="HadithTextFontname"/>
    <xsl:param name="HadithTextFontsize"/>
    <xsl:param name="HadithTextForecolor"/>
    <xsl:param name="HadithTextItalic"/>
    <xsl:param name="HadithTextUnderline"/>
    <xsl:param name="HadithTranslationBackcolor"/>
    <xsl:param name="HadithTranslationBold"/>
    <xsl:param name="HadithTranslationFontname"/>
    <xsl:param name="HadithTranslationFontsize"/>
    <xsl:param name="HadithTranslationForecolor"/>
    <xsl:param name="HadithTranslationUnderline"/>
    <xsl:param name="ShowHadithAya"/>
    <xsl:param name="ShowHadithBeginning"/>
    <xsl:param name="ShowHadithCode"/>
    <xsl:param name="ShowHadithGlossary"/>
    <xsl:param name="ShowHadithHemistich"/>
    <xsl:param name="ShowHadithMainNarrator"/>
    <xsl:param name="ShowHadithOtherNarrator"/>
    <xsl:param name="ShowHadithRef"/>
    <xsl:param name="ShowHadithText"/>
    <xsl:param name="ShowHadithTranslation"/>

    <xsl:param name="ShowHadithDivider"/>
    <xsl:param name="ShowHadithLiner"/>

    <xsl:template match="/">
        <w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
            <w:body>

                <xsl:for-each select="Paras/Para">

                    <xsl:if test="$ShowHadithCode">
                        <w:p w:rsidR="00F1582B" w:rsidRPr="00F1582B" w:rsidRDefault="00F1582B" w:rsidP="00F1582B">
                            <w:pPr>
                                <w:bidi/>
                                <w:rPr>
                                    <w:rFonts w:hint="cs"/>
                                    <w:rtl/>
                                </w:rPr>
                            </w:pPr>
                            <w:r w:rsidRPr="00F1582B">
                                <w:rPr>
                                    <w:rtl/>
                                    <w:rFonts w:hint="cs">
                                        <xsl:attribute name="w:cs">
                                            <xsl:value-of select="$HadithCodeFontname"/>
                                        </xsl:attribute>
                                    </w:rFonts>
                                    <w:sz>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithCodeFontsize"/>
                                        </xsl:attribute>
                                    </w:sz>
                                    <w:szCs>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithCodeFontsize"/>
                                        </xsl:attribute>
                                    </w:szCs>
                                    <xsl:if test="$HadithCodeBold">
                                        <w:b/>
                                        <w:bCs/>
                                    </xsl:if>
                                    <xsl:if test="$HadithCodeItalic">
                                        <w:i/>
                                        <w:iCs/>
                                    </xsl:if>
                                    <xsl:if test="$HadithCodeUnderline">
                                        <w:u w:val="single"/>
                                    </xsl:if>
                                    <w:color>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithCodeForecolor"/>
                                        </xsl:attribute>
                                    </w:color>
                                    <xsl:if test="not($HadithCodeBackcolor='transparent')">
                                        <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                            <xsl:attribute name="w:fill">
                                                <xsl:value-of select="$HadithCodeBackcolor"/>
                                            </xsl:attribute>
                                        </w:shd>
                                    </xsl:if>
                                </w:rPr>
                                <w:t xml:space="preserve">کد : <xsl:value-of select="@code"/> </w:t>
                            </w:r>
                        </w:p>
                    </xsl:if>

                    <w:p w:rsidR="005E438A" w:rsidRDefault="005E438A" w:rsidP="00F00F15">
                        <w:pPr>
                            <xsl:if test="$ShowHadithDivider and not($ShowHadithRef)">
                                <w:pBdr>
                                    <w:bottom w:val="single" w:sz="4" w:space="1" w:color="auto"/>
                                </w:pBdr>
                            </xsl:if>

                            <w:bidi/>
                            <w:spacing w:after="480"/>
                            <w:rPr>
                                <w:rtl/>
                                <w:lang w:bidi="fa-IR"/>
                            </w:rPr>
                        </w:pPr>

                        <xsl:for-each select="Runs/Run">

                            <xsl:if test="Footnote[@fid]">
                                <w:r w:rsidRPr="00C03713">
                                    <w:rPr>
                                        <w:rStyle w:val="FootnoteReference"/>
                                        <w:rFonts w:cs="B Mitra"/>
                                        <w:sz w:val="32"/>
                                        <w:szCs w:val="32"/>
                                        <w:rtl/>
                                    </w:rPr>

                                    <xsl:apply-templates select="Footnote"/>

                                </w:r>
                            </xsl:if>

                            <xsl:if test="not(@type = 'ft')">
                                <w:r w:rsidRPr="00C03713">
                                    <w:rPr>
                                        <w:rtl/>

                                        <w:rFonts w:hint="cs">
                                            <xsl:attribute name="w:cs">
                                                <xsl:value-of select="$HadithTextFontname"/>
                                            </xsl:attribute>
                                        </w:rFonts>
                                        <w:sz>
                                            <xsl:attribute name="w:val">
                                                <xsl:value-of select="$HadithTextFontsize"/>
                                            </xsl:attribute>
                                        </w:sz>
                                        <w:szCs>
                                            <xsl:attribute name="w:val">
                                                <xsl:value-of select="$HadithTextFontsize"/>
                                            </xsl:attribute>
                                        </w:szCs>
                                        <xsl:if test="$HadithTextBold">
                                            <w:b/>
                                            <w:bCs/>
                                        </xsl:if>
                                        <xsl:if test="$HadithTextItalic">
                                            <w:i/>
                                            <w:iCs/>
                                        </xsl:if>
                                        <xsl:if test="$HadithTextUnderline">
                                            <w:u w:val="single"/>
                                        </xsl:if>
                                        <w:color>
                                            <xsl:attribute name="w:val">
                                                <xsl:value-of select="$HadithTextForecolor"/>
                                            </xsl:attribute>
                                        </w:color>
                                        <xsl:if test="not($HadithTextBackcolor='transparent')">
                                            <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                                <xsl:attribute name="w:fill">
                                                    <xsl:value-of select="$HadithTextBackcolor"/>
                                                </xsl:attribute>
                                            </w:shd>
                                        </xsl:if>

                                        <xsl:if test="contains(Tags, 'highlight')">
                                            <w:color w:val="FF0000"/>
                                            <w:highlight w:val="yellow"/>
                                        </xsl:if>

                                        <xsl:if test="$ShowHadithBeginning and contains(Tags, 'beginning')">
                                            <w:rFonts w:hint="cs">
                                                <xsl:attribute name="w:cs">
                                                    <xsl:value-of select="$HadithBeginningFontname"/>
                                                </xsl:attribute>
                                            </w:rFonts>
                                            <w:sz>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithBeginningFontsize"/>
                                                </xsl:attribute>
                                            </w:sz>
                                            <w:szCs>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithBeginningFontsize"/>
                                                </xsl:attribute>
                                            </w:szCs>
                                            <xsl:if test="$HadithBeginningBold">
                                                <w:b/>
                                                <w:bCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithBeginningItalic">
                                                <w:i/>
                                                <w:iCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithBeginningUnderline">
                                                <w:u w:val="single"/>
                                            </xsl:if>
                                            <w:color>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithBeginningForecolor"/>
                                                </xsl:attribute>
                                            </w:color>
                                            <xsl:if test="not($HadithBeginningBackcolor='transparent')">
                                                <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                                    <xsl:attribute name="w:fill">
                                                        <xsl:value-of select="$HadithBeginningBackcolor"/>
                                                    </xsl:attribute>
                                                </w:shd>
                                            </xsl:if>
                                        </xsl:if>

                                        <xsl:if test="$ShowHadithMainNarrator and contains(Tags, 'mainnarrator')">
                                            <w:rFonts w:hint="cs">
                                                <xsl:attribute name="w:cs">
                                                    <xsl:value-of select="$HadithMainNarratorFontname"/>
                                                </xsl:attribute>
                                            </w:rFonts>
                                            <w:sz>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithMainNarratorFontsize"/>
                                                </xsl:attribute>
                                            </w:sz>
                                            <w:szCs>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithMainNarratorFontsize"/>
                                                </xsl:attribute>
                                            </w:szCs>
                                            <xsl:if test="$HadithMainNarratorBold">
                                                <w:b/>
                                                <w:bCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithMainNarratorItalic">
                                                <w:i/>
                                                <w:iCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithMainNarratorUnderline">
                                                <w:u w:val="single"/>
                                            </xsl:if>
                                            <w:color>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithMainNarratorForecolor"/>
                                                </xsl:attribute>
                                            </w:color>
                                            <xsl:if test="not($HadithMainNarratorBackcolor='transparent')">
                                                <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                                    <xsl:attribute name="w:fill">
                                                        <xsl:value-of select="$HadithMainNarratorBackcolor"/>
                                                    </xsl:attribute>
                                                </w:shd>
                                            </xsl:if>
                                        </xsl:if>

                                        <xsl:if test="$ShowHadithOtherNarrator and contains(Tags, 'othernarrator')">
                                            <w:rFonts w:hint="cs">
                                                <xsl:attribute name="w:cs">
                                                    <xsl:value-of select="$HadithOtherNarratorFontname"/>
                                                </xsl:attribute>
                                            </w:rFonts>
                                            <w:sz>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithOtherNarratorFontsize"/>
                                                </xsl:attribute>
                                            </w:sz>
                                            <w:szCs>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithOtherNarratorFontsize"/>
                                                </xsl:attribute>
                                            </w:szCs>
                                            <xsl:if test="$HadithOtherNarratorBold">
                                                <w:b/>
                                                <w:bCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithOtherNarratorItalic">
                                                <w:i/>
                                                <w:iCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithOtherNarratorUnderline">
                                                <w:u w:val="single"/>
                                            </xsl:if>
                                            <w:color>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithOtherNarratorForecolor"/>
                                                </xsl:attribute>
                                            </w:color>
                                            <xsl:if test="not($HadithOtherNarratorBackcolor='transparent')">
                                                <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                                    <xsl:attribute name="w:fill">
                                                        <xsl:value-of select="$HadithOtherNarratorBackcolor"/>
                                                    </xsl:attribute>
                                                </w:shd>
                                            </xsl:if>
                                        </xsl:if>

                                        <xsl:if test="$ShowHadithAya and contains(Tags, 'aya')">
                                            <w:rFonts w:hint="cs">
                                                <xsl:attribute name="w:cs">
                                                    <xsl:value-of select="$HadithAyaFontname"/>
                                                </xsl:attribute>
                                            </w:rFonts>
                                            <w:sz>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithAyaFontsize"/>
                                                </xsl:attribute>
                                            </w:sz>
                                            <w:szCs>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithAyaFontsize"/>
                                                </xsl:attribute>
                                            </w:szCs>
                                            <xsl:if test="$HadithAyaBold">
                                                <w:b/>
                                                <w:bCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithAyaItalic">
                                                <w:i/>
                                                <w:iCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithAyaUnderline">
                                                <w:u w:val="single"/>
                                            </xsl:if>
                                            <w:color>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithAyaForecolor"/>
                                                </xsl:attribute>
                                            </w:color>
                                            <xsl:if test="not($HadithAyaBackcolor='transparent')">
                                                <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                                    <xsl:attribute name="w:fill">
                                                        <xsl:value-of select="$HadithAyaBackcolor"/>
                                                    </xsl:attribute>
                                                </w:shd>
                                            </xsl:if>
                                        </xsl:if>

                                        <xsl:if test="$ShowHadithGlossary and contains(Tags, 'glossary')">
                                            <w:rFonts w:hint="cs">
                                                <xsl:attribute name="w:cs">
                                                    <xsl:value-of select="$HadithGlossaryFontname"/>
                                                </xsl:attribute>
                                            </w:rFonts>
                                            <w:sz>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithGlossaryFontsize"/>
                                                </xsl:attribute>
                                            </w:sz>
                                            <w:szCs>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithGlossaryFontsize"/>
                                                </xsl:attribute>
                                            </w:szCs>
                                            <xsl:if test="$HadithGlossaryBold">
                                                <w:b/>
                                                <w:bCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithGlossaryItalic">
                                                <w:i/>
                                                <w:iCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithGlossaryUnderline">
                                                <w:u w:val="single"/>
                                            </xsl:if>
                                            <w:color>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithGlossaryForecolor"/>
                                                </xsl:attribute>
                                            </w:color>
                                            <xsl:if test="not($HadithGlossaryBackcolor='transparent')">
                                                <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                                    <xsl:attribute name="w:fill">
                                                        <xsl:value-of select="$HadithGlossaryBackcolor"/>
                                                    </xsl:attribute>
                                                </w:shd>
                                            </xsl:if>
                                        </xsl:if>

                                        <xsl:if test="$ShowHadithHemistich and contains(Tags, 'hemistich')">
                                            <w:rFonts w:hint="cs">
                                                <xsl:attribute name="w:cs">
                                                    <xsl:value-of select="$HadithHemistichFontname"/>
                                                </xsl:attribute>
                                            </w:rFonts>
                                            <w:sz>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithHemistichFontsize"/>
                                                </xsl:attribute>
                                            </w:sz>
                                            <w:szCs>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithHemistichFontsize"/>
                                                </xsl:attribute>
                                            </w:szCs>
                                            <xsl:if test="$HadithHemistichBold">
                                                <w:b/>
                                                <w:bCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithHemistichItalic">
                                                <w:i/>
                                                <w:iCs/>
                                            </xsl:if>
                                            <xsl:if test="$HadithHemistichUnderline">
                                                <w:u w:val="single"/>
                                            </xsl:if>
                                            <w:color>
                                                <xsl:attribute name="w:val">
                                                    <xsl:value-of select="$HadithHemistichForecolor"/>
                                                </xsl:attribute>
                                            </w:color>
                                            <xsl:if test="not($HadithHemistichBackcolor='transparent')">
                                                <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                                    <xsl:attribute name="w:fill">
                                                        <xsl:value-of select="$HadithHemistichBackcolor"/>
                                                    </xsl:attribute>
                                                </w:shd>
                                            </xsl:if>
                                        </xsl:if>

                                    </w:rPr>
                                    <w:t xml:space="preserve"><xsl:value-of select="Text"/></w:t>
                                </w:r>
                            </xsl:if>

                            <xsl:if test="$ShowHadithLiner and contains(Tags, 'liner')">
                                <w:r>
                                    <w:rPr>
                                        <w:rtl/>
                                    </w:rPr>
                                    <w:br/>
                                </w:r>
                            </xsl:if>

                        </xsl:for-each>
                    </w:p>

                    <xsl:if test="$ShowHadithTranslation">
                        <w:p w:rsidR="00F1582B" w:rsidRPr="00F1582B" w:rsidRDefault="00F1582B" w:rsidP="00F1582B">
                            <w:pPr>
                                <w:bidi/>
                                <w:spacing w:after="480"/>
                                <w:rPr>
                                    <w:rtl/>
                                    <w:lang w:bidi="fa-IR"/>
                                </w:rPr>
                            </w:pPr>
                            <w:r w:rsidRPr="00F1582B">
                                <w:rPr>
                                    <w:rtl/>
                                    <w:rFonts w:hint="cs">
                                        <xsl:attribute name="w:cs">
                                            <xsl:value-of select="$HadithTranslationFontname"/>
                                        </xsl:attribute>
                                    </w:rFonts>
                                    <w:sz>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithTranslationFontsize"/>
                                        </xsl:attribute>
                                    </w:sz>
                                    <w:szCs>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithTranslationFontsize"/>
                                        </xsl:attribute>
                                    </w:szCs>
                                    <xsl:if test="$HadithTranslationBold">
                                        <w:b/>
                                        <w:bCs/>
                                    </xsl:if>
                                    <xsl:if test="$HadithTranslationItalic">
                                        <w:i/>
                                        <w:iCs/>
                                    </xsl:if>
                                    <xsl:if test="$HadithTranslationUnderline">
                                        <w:u w:val="single"/>
                                    </xsl:if>
                                    <w:color>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithTranslationForecolor"/>
                                        </xsl:attribute>
                                    </w:color>
                                    <xsl:if test="not($HadithTranslationBackcolor='transparent')">
                                        <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                            <xsl:attribute name="w:fill">
                                                <xsl:value-of select="$HadithTranslationBackcolor"/>
                                            </xsl:attribute>
                                        </w:shd>
                                    </xsl:if>
                                </w:rPr>
                                <w:t xml:space="preserve"><xsl:value-of select="Trans"/></w:t>
                            </w:r>
                        </w:p>
                    </xsl:if>

                    <xsl:if test="$ShowHadithRef">
                        <w:p w:rsidR="00F1582B" w:rsidRPr="00F1582B" w:rsidRDefault="00F1582B" w:rsidP="00F1582B">
                            <w:pPr>
                                <w:bidi/>
                                <xsl:if test="$ShowHadithDivider">
                                    <w:pBdr>
                                        <w:bottom w:val="single" w:sz="4" w:space="1" w:color="auto"/>
                                    </w:pBdr>
                                </xsl:if>
                                <w:rPr>
                                    <w:rFonts w:hint="cs"/>
                                    <w:rtl/>
                                </w:rPr>
                            </w:pPr>
                            <w:r w:rsidRPr="00F1582B">
                                <w:rPr>
                                    <w:rtl/>
                                    <w:rFonts w:hint="cs">
                                        <xsl:attribute name="w:cs">
                                            <xsl:value-of select="$HadithRefFontname"/>
                                        </xsl:attribute>
                                    </w:rFonts>
                                    <w:sz>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithRefFontsize"/>
                                        </xsl:attribute>
                                    </w:sz>
                                    <w:szCs>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithRefFontsize"/>
                                        </xsl:attribute>
                                    </w:szCs>
                                    <xsl:if test="$HadithRefBold">
                                        <w:b/>
                                        <w:bCs/>
                                    </xsl:if>
                                    <xsl:if test="$HadithRefItalic">
                                        <w:i/>
                                        <w:iCs/>
                                    </xsl:if>
                                    <xsl:if test="$HadithRefUnderline">
                                        <w:u w:val="single"/>
                                    </xsl:if>
                                    <w:color>
                                        <xsl:attribute name="w:val">
                                            <xsl:value-of select="$HadithRefForecolor"/>
                                        </xsl:attribute>
                                    </w:color>
                                    <xsl:if test="not($HadithRefBackcolor='transparent')">
                                        <w:shd w:val="clear" w:color="auto" w:themeFill="accent1" w:themeFillTint="33">
                                            <xsl:attribute name="w:fill">
                                                <xsl:value-of select="$HadithRefBackcolor"/>
                                            </xsl:attribute>
                                        </w:shd>
                                    </xsl:if>
                                </w:rPr>
                                <w:t xml:space="preserve"><xsl:value-of select="Source"/></w:t>
                            </w:r>
                        </w:p>
                    </xsl:if>

                </xsl:for-each>

                <w:sectPr w:rsidR="00F00F15" w:rsidRPr="001E576B" w:rsidSect="00DE3589">
                    <w:pgSz w:w="12240" w:h="15840"/>
                    <w:pgMar w:top="1440" w:right="1440" w:bottom="1440" w:left="1440" w:header="720" w:footer="720"
                             w:gutter="0"/>
                    <w:cols w:space="720"/>
                    <w:docGrid w:linePitch="360"/>
                </w:sectPr>

            </w:body>
        </w:document>
    </xsl:template>


    <xsl:template match="Footnote" xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
        <w:footnoteReference>
            <xsl:apply-templates select="@*|node()"/>
        </w:footnoteReference>
    </xsl:template>

    <xsl:template match="@fid" xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
        <xsl:attribute name="w:id">
            <xsl:value-of select="."/>
        </xsl:attribute>
    </xsl:template>

</xsl:stylesheet>
