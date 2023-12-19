import os
import re
import shutil


def remove_between_markers(content):
  pattern = re.compile(r"//=== TODO ===.*?//=== END ===", re.DOTALL)
  return re.sub(pattern, "// TODO \n\n", content)

def task_repo(src_dir, dest_dir, ignore_list):
  for root, dirs, files in os.walk(src_dir):
    dirs[:] = [d for d in dirs if d not in ignore_list]
    for file_name in files:
      if file_name in ignore_list:
        continue
      file_path = os.path.join(root, file_name)
      if file_path.endswith(".java"):
        with open(file_path, "r") as f:
          content = f.read()
        modified_content = remove_between_markers(content)
        dest_file_path = os.path.join(dest_dir, os.path.relpath(file_path, src_dir))
        os.makedirs(os.path.dirname(dest_file_path), exist_ok=True)
      
        with open(dest_file_path, "w") as f:
          f.write(modified_content)
      else:
        dest_file_path = os.path.join(dest_dir, os.path.relpath(file_path, src_dir))
        os.makedirs(os.path.dirname(dest_file_path), exist_ok=True)
        shutil.copyfile(file_path, dest_file_path)
        
if __name__ == "__main__":
  src_dir = os.path.join(os.getcwd())
  dest_dir = os.path.join(os.getcwd(), "task_repo")
  ignore_list = ['.git', '.gradle', 'generate_task_repo.py']
  print(dest_dir)
  task_repo(src_dir, dest_dir, ignore_list)
  