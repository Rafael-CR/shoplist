package com.rrcr.shoplist.generics

import com.rrcr.shoplist.pojos.Producto
import org.jsoup.Jsoup

class Utils {
    companion object {
        @JvmStatic
        fun parseSurtiTienda(html: String?): Producto? {
            var producto: Producto? = null
            val doc = Jsoup.parse(html)
            val spanElement = doc.select("h3.product-item__name a") // nombre
            val imageElement = doc.select("figure.product-item__figure a img") // imagen
            var nombre: String
            if (spanElement.size > 0) {
                producto = Producto()
                val elemento = spanElement.first()
                producto.nombre = elemento.childNodes()[0].toString()
                if (imageElement.size > 0) {
                    producto.urlImage = imageElement.first().attr("src")
                }
            }
            return producto
        }

        @JvmStatic
        fun parseAmazon(html: String?): Producto? {
            var producto: Producto? = null
            if (html?.indexOf("div class=\"a-section a-spacing-base\"") != -1) {
                val reducedHtml = "<" + html?.substring(
                    html.indexOf("div class=\"a-section a-spacing-base\""),
                    html.indexOf("div class=\"a-section a-spacing-base\"") + 3000,
                )
                val doc = Jsoup.parse(reducedHtml)
                val image = doc.select("img")
                if (image.size > 0) {
                    producto = Producto()
                    producto.nombre = image.attr("alt")
                    producto.urlImage = image.attr("src")
                }
            }

            return producto
        }

        @JvmStatic
        fun parseSoriana(html: String?): Producto? {
            var producto: Producto? = null
            if (html?.indexOf("div class=\"product\"") != -1) {
                val reducedHtml = "<" + html?.substring(
                    html.indexOf("div class=\"product\""),
                    html.indexOf("div class=\"product\"") + 3000,
                )
                val doc = Jsoup.parse(reducedHtml)
                val productsElements = doc.select("div.product")
                val image = productsElements.first().select("a img")
                if (image.size > 0) {
                    producto = Producto()
                    producto.nombre = image.attr("alt")
                    producto.urlImage = image.attr("data-src")
                }
            }

            return producto
        }

        @JvmStatic
        fun parseWalmart(html: String?): Producto? {
            var producto: Producto? = null
            if (html?.indexOf("article class=\"vtex-product-summary-2-x-element pointer pt3 pb4 flex flex-column h-100\"") != -1) {
                val reducedHtml = "<" + html?.substring(
                    html.indexOf("article class=\"vtex-product-summary-2-x-element pointer pt3 pb4 flex flex-column h-100\""),
                    html.indexOf("article class=\"vtex-product-summary-2-x-element pointer pt3 pb4 flex flex-column h-100\"") + 3000,
                )
                val doc = Jsoup.parse(reducedHtml)
                val image = doc.select("img")
                if (image.size > 0) {
                    producto = Producto()
                    producto.nombre = image.attr("alt")
                    producto.urlImage = image.attr("src")
                }
            }

            return producto
        }
    }
}
