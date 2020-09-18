using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WCF_DolazakNaPosao
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class Prijava : IPrijava
    {

        Dictionary<string, Log> ulogovani;
        Dictionary<string, List<string>> dolasci;
        
        public Prijava()
        {
            ulogovani = new Dictionary<string, Log>();
            dolasci = new Dictionary<string, List<string>>();
        }

        public bool OdjaviSe(string ime)
        {
            if (!ulogovani.ContainsKey(ime))
                return false;

            List<string> mojiDolasci = dolasci[ime];

            Log login = ulogovani[ime];
            Log logout = new Log(true);
            TimeSpan provedeno = logout.vreme - login.vreme;
           
            mojiDolasci.Add($"{ime} ulogovan: (time: {login.vreme.ToString()}, uspesno: {login.uspesno.ToString()}) izlogovan: (time: {logout.vreme.ToString()},uspesno: {logout.uspesno.ToString()}) provedeno vreme: {provedeno.ToString()}");

            ulogovani.Remove(ime);

            return true;
        }

        public bool PrijaviSe(string ime)
        {
            if(ulogovani.ContainsKey(ime))
                return false;

            Log log = new Log(true);
            ulogovani.Add(ime, log);
            if(!dolasci.ContainsKey(ime))
                dolasci.Add(ime, new List<string>());

            return true;
        }

        public List<string> VratiProvedenoVreme(string ime)
        {
            return this.dolasci[ime];
        }

        public List<string> VratiSve()
        {
            List<string> result = new List<string>();

            foreach(List<string> sviDolasciRadnika in dolasci.Values)
                foreach (string dolazak in sviDolasciRadnika)
                    result.Add(dolazak);

            return result;
        }
    }
}
