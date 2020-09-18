using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WCF_Cisterna
{
    public class Cisterna : ICisterna
    {
        List<string> promene;
        Stanje trenutnoStanje;
        public Cisterna()
        {
            trenutnoStanje = new Stanje()
            {
                Gustina = 1,
                Zapremina = 0
            };
            promene = new List<string>();
        }
        public void DodajMaterijal(string ime,double zapremina,double gustina)
        {
            double masa = trenutnoStanje.Gustina * trenutnoStanje.Zapremina;
            masa += zapremina * gustina;
            trenutnoStanje.Zapremina += zapremina;
            trenutnoStanje.Gustina = masa / trenutnoStanje.Zapremina;
            promene.Add("");
        }
        public void IspustiZapreminu(double zapremina)
        {
            trenutnoStanje.Zapremina -= zapremina;
            promene.Add("");
        }
        public Stanje PrikaziTrenutnoStanje()
        {
            return trenutnoStanje;
        }
        
        public List<string> IzlistajSvePromene()
        {
            return promene;
        }
    }
}
