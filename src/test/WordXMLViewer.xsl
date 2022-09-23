<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:w="http://schemas.microsoft.com/office/word/2003/wordml"
    xmlns:ext="http://wordviewer.com" 
    xmlns:v="urn:schemas-microsoft-com:vml" 
    xmlns:o="urn:schemas-microsoft-com:office:office"
    xmlns:WX="http://schemas.microsoft.com/office/word/2003/auxHint"
	xmlns:aml="http://schemas.microsoft.com/aml/2001/core"
	xmlns:w10="urn:schemas-microsoft-com:office:word"
    exclude-result-prefixes="w ext v o WX aml w10">
    
    <xsl:import href="word2html.xsl"/>

    <xsl:key name="binDataKey" match="w:binData" use="@w:name"/>
    
    <xsl:template match="w:binData"/>    

    <xsl:template match="v:imagedata">
        <!-- Temporary image URL -->
        <!--<xsl:variable name="imageSrc" select="ext:extractImage(key('binDataKey', @src))"/>-->
        <xsl:variable name="imageSrc"/>
        <!-- If VML is supported, use <v:imagedata> -->
        <xsl:text disable-output-escaping="yes">&lt;!--[if vml]&gt;</xsl:text>
        <v:imagedata src="{$imageSrc}">
           <!--  <xsl:copy-of select="@*[not(name()='src')]"/> -->
        </v:imagedata>
        <xsl:text disable-output-escaping="yes">&lt;![endif]--&gt;</xsl:text>        
        <!-- For browsers not supporting VML render regular <img> tag -->
        <xsl:text disable-output-escaping="yes">&lt;![if !vml]&gt;</xsl:text>
        <!--<img src="{$imageSrc}" style="{ancestor::w:pict/v:*/@style[1]}" alt="{ancestor::w:pict/v:shape/v:imagedata/@o:title}"         
        title="{ancestor::w:pict/v:shape/v:imagedata/@o:title}"/> -->
        <xsl:text disable-output-escaping="yes">&lt;![endif]&gt;</xsl:text>        
    </xsl:template>    
    
    <xsl:template match="w:hdr|w:ftr">
        <xsl:apply-templates/>
    </xsl:template>

</xsl:stylesheet>