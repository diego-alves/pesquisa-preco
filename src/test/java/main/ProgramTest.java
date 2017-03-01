package main;

import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by diego on 28/02/17.
 */
public class ProgramTest extends FluentTest {

    enum Store {
        CARREFOUR(
                "https://www.carrefour.com.br/Smart-TV-LED-49-Samsung-KS7000-SUHD-4K-4-HDMI-3-USB-Prata/p/9966820",
                "Smart TV LED 49\" Samsung KS7000 SUHD 4K 4 HDMI 3 USB Prata | Carrefour",
                "meta[itemprop=\"price\"]", "content"),
        FASTSHOP(
                "https://www.fastshop.com.br/loja/smart-tv-samsung-led-4k-49-wi-fi-un49ks7000gxzd-fast",
                "Smart TV LED Samsung 4K 49” com 04 Entradas HDMI",
                "input#prdPrice", "value"),
        MAGAZINE_LUIZA(
                "http://www.magazineluiza.com.br/smart-tv-led-49-samsung-4k-ultra-hd-49ks7000-conversor-digital-4-hdmi-3-usb/p/1933894/et/tv4k/",
                "Smart TV LED 49\" Samsung 4K Ultra HD 49KS7000 Conversor Digital 4 HDMI 3 USB - Tv 4k - Magazine Luiza",
                "input[name=\"productCashPrice\"]", "value"),
        EXTRA(
                "http://www.extra.com.br/Eletronicos/Televisores/TV4K/Smart-TV-LED-49-SUHD-4K-Samsung-49KS7000-com-Pontos-Quanticos-HDR-1000-Sistema-Tizen-One-Control-Design-360°-Ultra-Slim-Quadcore-HDMI-e-USB-8726058.html?recsource=busca-int&rectype=busca-2536",
                "Smart TV LED 49\" SUHD 4K Samsung 49KS7000 com Pontos Quânticos, HDR 1000, Sistema Tizen, One Control, Design 360° Ultra Slim, Quadcore, HDMI e USB - TV 4K no Extra.com.br",
                "meta[itemprop=\"price\"]","content")
        ;


        private final String url;

        private final String title;

        private final String element;

        private final String attribute;

        Store(String url, String title, String element, String attribute) {
            this.url = url;
            this.title = title;
            this.element = element;
            this.attribute = attribute;
        }

        public void go(FluentTest f){
            f.goTo(this.url);
            assertThat(f.window().title()).contains(this.title);
        }

        public double getPrice(FluentTest f) {
            String attribute = f.$(this.element).attribute(this.attribute);
            return attribute != null ? Double.valueOf(attribute) : -1;
        }
    }


    @Test
    public void testMain(){

        for (Store store : Store.values()) {
            store.go(this);
            double price = store.getPrice(this);
            if(price > 0d) {
                System.out.println(store + "\t\t" + price);
            } else {
                System.out.println(store + "\t\t" + "Não tem.");
            }
        }

    }
}
