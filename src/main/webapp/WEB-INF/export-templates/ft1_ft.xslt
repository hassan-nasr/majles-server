<?xml version='1.0' encoding='utf-8'?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">

        <w:footnotes
                xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                >

            <w:footnote w:type="separator" w:id="-1">
                <w:p w:rsidR="008D61A1" w:rsidRDefault="008D61A1" w:rsidP="005E438A">
                    <w:pPr>
                        <w:spacing w:after="0" w:line="240" w:lineRule="auto" />
                    </w:pPr>
                    <w:r>
                        <w:separator />
                    </w:r>
                </w:p>
            </w:footnote>
            <w:footnote w:type="continuationSeparator" w:id="0">
                <w:p w:rsidR="008D61A1" w:rsidRDefault="008D61A1" w:rsidP="005E438A">
                    <w:pPr>
                        <w:spacing w:after="0" w:line="240" w:lineRule="auto" />
                    </w:pPr>
                    <w:r>
                        <w:continuationSeparator />
                    </w:r>
                </w:p>
            </w:footnote>

            <!--<xsl:for-each select="Footnotes/Footnote">-->
            <xsl:for-each select="Footnotes/Footnote">
                <xsl:apply-templates select="Text"/>
            </xsl:for-each>

        </w:footnotes>

    </xsl:template>

    <xsl:template match="Text" xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
        <w:footnote>
            <xsl:attribute name="w:id">
                <xsl:value-of select="@id"/>
            </xsl:attribute>

            <w:p w:rsidR="005E438A" w:rsidRPr="002B7104" w:rsidRDefault="005E438A" w:rsidP="005E438A">
                <w:pPr>
                    <w:pStyle w:val="FootnoteText" />
                    <w:bidi />
                    <w:rPr>
                        <w:rFonts w:cs="B Mitra" />
                        <w:sz w:val="22" />
                        <w:szCs w:val="22" />
                        <w:rtl />
                    </w:rPr>
                </w:pPr>
                <w:r w:rsidRPr="002B7104">
                    <w:rPr>
                        <w:rStyle w:val="FootnoteReference" />
                        <w:rFonts w:cs="B Mitra" />
                        <w:sz w:val="22" />
                        <w:szCs w:val="22" />
                    </w:rPr>
                    <w:footnoteRef />
                </w:r>
                <w:r w:rsidRPr="002B7104">
                    <w:rPr>
                        <w:rFonts w:cs="B Mitra" />
                        <w:sz w:val="22" />
                        <w:szCs w:val="22" />
                    </w:rPr>
                    <w:t xml:space="preserve"> </w:t>
                </w:r>
                <w:r w:rsidRPr="002B7104">
                    <w:rPr>
                        <w:rFonts w:cs="B Mitra" w:hint="cs" />
                        <w:sz w:val="22" />
                        <w:szCs w:val="22" />
                        <w:rtl />
                    </w:rPr>
                    <w:t xml:space="preserve"><xsl:value-of select="."/></w:t>
                </w:r>
            </w:p>

        </w:footnote>
    </xsl:template>


</xsl:stylesheet>
