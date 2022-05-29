using System;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace lab1_csharp
{
	class Program
	{
		static void Main(string[] args)
		{
			string path = args[0];
			if (path.Length == 0)
			{
				Console.Error.WriteLine("No input");
				return;
			}
			if (Directory.Exists(path) == false)
			{
				Console.Error.WriteLine("Wrong path");
				return;
			}
			DirectoryInfo directoryInfo = new DirectoryInfo(path);
			DirectoryInfoExt directoryExt = new DirectoryInfoExt(directoryInfo);
			directoryExt.Print(0);
			Console.WriteLine("Latest file: {0}", directoryInfo.GetLatestFile());
			MakeCollection(path);
			Console.ReadLine();
		}

		public static void MakeCollection(string path)
		{
			SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new Comparer());
			if (File.Exists(path))
			{
				FileInfo file = new FileInfo(path);
				collection.Add(file.Name, (int)file.Length);
			}
			else if (Directory.Exists(path))
			{
				DirectoryInfo dir = new DirectoryInfo(path);
				foreach (var subdir in dir.GetDirectories())
					collection.Add(subdir.Name, (subdir.GetFiles().Length + subdir.GetDirectories().Length));
				foreach (var file in dir.GetFiles())
					collection.Add(file.Name, (int)file.Length);
			}
			FileStream fileStream = new FileStream("DataFile.dat", FileMode.Create);
			BinaryFormatter binaryFormatter = new BinaryFormatter();
			try
			{
				binaryFormatter.Serialize(fileStream, collection);
			}
			catch (SerializationException ex)
			{
				Console.WriteLine("Serialization error: {0}", ex.Message);
			}
			fileStream.Close();
			Deserialize();
		}

		public static void Deserialize()
		{
			SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new Comparer());
			FileStream fs = new FileStream("DataFile.dat", FileMode.Open);
			try
			{
				BinaryFormatter bf = new BinaryFormatter();
				collection = (SortedDictionary<string, int>)bf.Deserialize(fs);
			}
			catch (SerializationException ex)
			{
				Console.WriteLine("Serialization error: {0}", ex.Message);
			}
			foreach (var dir in collection)
				Console.WriteLine("{0} -> {1}", dir.Key, dir.Value);
		}
	}
}
