using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Klijent.LotoServiceReference;

namespace Klijent
{
    class ObavestiCallback : LotoServiceReference.ILotoCallback
    {
        //public delegate void PrintIt(string text);
        //PrintIt printClient;
        public string Nadimak { get; set; }
        public ObavestiCallback(string nadimak/*, PrintIt printFunc*/)
        {
            //printClient = printFunc;
            this.Nadimak = nadimak;
        }
        public void NoviBroj(int broj)
        {
            //this.printClient($"Izvucen je broj {broj}!");
            Console.WriteLine($"Izvucen je broj {broj}!");
        }

        public void ObavestiPobednika(string nadminak, int brojIzvucenihBrojeva)
        {
            if (Nadimak.CompareTo(nadminak) == 0)
            {
                switch (brojIzvucenihBrojeva)
                {
                    case 5:
                        //this.printClient("Cestitamo! Izvukli ste peticu na lotou!");
                        Console.WriteLine("Cestitamo! Izvukli ste peticu na lotou!");
                        break;
                    case 6:
                        Console.WriteLine("Cestitamo! Izvukli ste sesticu na lotou!");
                        break;
                    case 7:
                        Console.WriteLine("CESTITAMO!!! DOBILI STE SEDMICU NA LOTOU!");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
