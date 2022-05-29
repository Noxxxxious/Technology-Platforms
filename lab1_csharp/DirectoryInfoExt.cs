using System;
using System.Collections.Generic;
using System.IO;

namespace lab1_csharp
{
	public class DirectoryInfoExt : DataExt
	{
		IList<DataExt> children;

		public DirectoryInfoExt(DirectoryInfo directoryInfo)
		{
			this.info = directoryInfo;
			this.children = new List<DataExt>();
			foreach (FileSystemInfo fsi in directoryInfo.EnumerateFileSystemInfos())
			{
				if (fsi.GetType() == typeof(FileInfo))
					this.children.Add(new FileInfoExt(fsi as FileInfo));
				else if (fsi.GetType() == typeof(DirectoryInfo))
					this.children.Add(new DirectoryInfoExt(fsi as DirectoryInfo));
			}
		}

		protected override string Format(int depth)
		{
			string format = "";
			for (int i = 0; i < depth; ++i)
				format += '\t';
			format += String.Format("{0} ({1}) {2}", info.Name, ((DirectoryInfo)info).GetFileSystemInfos().Length, info.GetDOSAttributes());
			return format;
		}

		protected internal override void Print(int recursionDepth)
		{
			Console.WriteLine(this.Format(recursionDepth));
			foreach (DataExt child in children)
				child.Print(recursionDepth + 1);
		}
	}
}
