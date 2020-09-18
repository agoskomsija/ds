using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Threading;

namespace WCF_LotoCB
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class Loto : ILoto
    {
        Dictionary<string, Kombinacija> registrovano;
        bool nijePoceloIzvlacenje = true;
        List<int> izvuceniBrojevi;
        Dictionary<string,IObavestiCallback> allCbs;
        IObavestiCallback callback
        {
            get
            {
                return OperationContext.Current.GetCallbackChannel<IObavestiCallback>();
            }
        }

        public Loto()
        {
            registrovano = new Dictionary<string, Kombinacija>();
            izvuceniBrojevi = new List<int>();
            allCbs = new Dictionary<string, IObavestiCallback>();
        }

        public bool DodajKombinaciju(string nadimak, Kombinacija kombinacija)
        {
            if(!allCbs.ContainsKey(nadimak))
            {
                allCbs.Add(nadimak, callback);
            }
            if (nijePoceloIzvlacenje)
            {
                registrovano.Add(nadimak, kombinacija);
                return true;
            }
            return false;
        }
        public void ObrisiKombinaciju(string nadimak)
        {
            if (nijePoceloIzvlacenje)
                registrovano.Remove(nadimak);
        }

        public void ZapocniIzvlacenje()
        {
            nijePoceloIzvlacenje = false;
            int izvuceniBroj;
            for (int i = 0; i < 7; i++)
            {
                izvuceniBroj = new Random().Next(0, 60);
                izvuceniBrojevi.Add(izvuceniBroj);
                
                foreach(IObavestiCallback c in allCbs.Values)
                    c.NoviBroj(izvuceniBroj);
                Thread.Sleep(1000);
            }
            Kombinacija izvucenaKombinacija = new Kombinacija(izvuceniBrojevi);
            foreach (var ucesnik in registrovano)
            {
                int pogodjeno = ucesnik.Value.Uporedi(izvucenaKombinacija);
                if (pogodjeno >= 5)
                    allCbs[ucesnik.Key].ObavestiPobednika(ucesnik.Key, pogodjeno);
            }
        }
    }
}
