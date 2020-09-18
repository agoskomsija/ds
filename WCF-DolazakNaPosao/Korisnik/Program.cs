using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Korisnik.PrijavaServiceReference;

namespace Korisnik
{
    class Program
    {
        static void Main(string[] args)
        {
            string ime = Console.ReadLine();
            bool result;
            PrijavaClient proxy = new PrijavaClient();
            result = proxy.PrijaviSe(ime);
            Console.WriteLine(result ? "Uspesno!" : "Neuspesno!");
            //Thread.Sleep(10000);
            result = proxy.OdjaviSe(ime);
            Console.WriteLine(result ? "Uspesno!" : "Neuspesno!");
            proxy.OdjaviSe(ime);
            Console.WriteLine(result ? "Uspesno!" : "Neuspesno!");

            List<string> ja, svi;
            ja = proxy.VratiProvedenoVreme(ime);
            svi = proxy.VratiSve();

            Console.WriteLine("moje vreme");
            foreach(string s in ja)
                Console.WriteLine(s);

            Console.WriteLine("svi izlistani");
            foreach(string s in svi)
                Console.WriteLine(s);


            Console.ReadLine();


        }
    }
}
