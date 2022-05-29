using System;
using System.IO;

namespace lab1_csharp
{
    public static class Extensions
    {
		public static string GetDOSAttributes(this FileSystemInfo fsi)
		{
			string[] att = { "-", "r", "a", "h", "s" };
			return att[fsi.Attributes.HasFlag(FileAttributes.ReadOnly) ? 1 : 0] +
					att[fsi.Attributes.HasFlag(FileAttributes.Archive) ? 2 : 0] +
					att[fsi.Attributes.HasFlag(FileAttributes.Hidden) ? 3 : 0] +
					att[fsi.Attributes.HasFlag(FileAttributes.System) ? 4 : 0];
		}

		public static DateTime GetLatestFile(this DirectoryInfo dir)
		{
			DateTime latest = DateTime.MinValue;
			foreach (var info in dir.EnumerateFileSystemInfos())
			{
				if (info.GetType() == typeof(DirectoryInfo))
				{
					DateTime current = GetLatestFile(info as DirectoryInfo);
					if (current < latest)
						latest = current;
				}
				else if (info.GetType() == typeof(FileInfo))
				{
					if (info.CreationTime > latest)
						latest = info.CreationTime;
				}
			}
			return latest;
		}
	}
}
