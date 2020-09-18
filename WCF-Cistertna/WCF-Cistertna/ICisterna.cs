using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WCF_Cisterna
{
    [ServiceContract]
    public interface ICisterna
    {
        void DodajMaterijal(string ime, double zapremina, double gustina);
        void IspustiZapreminu(double zapremina);
        Stanje PrikaziTrenutnoStanje();
        List<string> IzlistajSvePromene();
    }

    [DataContract]
    public class Stanje
    {

        [DataMember]
        public double Zapremina { get; set; }
        [DataMember]
        public double Gustina { get; set; }
    }
}
