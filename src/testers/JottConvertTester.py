import glob
import os
import subprocess
import unittest


class TestC(unittest.TestCase):

    def test_helloWorld(self):
        self._test_file("helloWorld.jott")
        
    def test_ifStmtReturns(self):
        self._test_file("ifStmtReturns.jott")
        
    def test_largerValid(self):
        self._test_file("largerValid.jott")
        
    def test_providedExample1(self):
        self._test_file("providedExample1.jott")
        
    def test_validLoop(self):
        self._test_file("validLoop.jott")

    def setUp(self):
        stdout, stderr, success = self.run_command('javac *.java *.java provided/*.java')
        self.assertTrue(success, f"Compilation of src failed: {stderr} {stdout}")
        
    @classmethod
    def tearDownClass(self):
        def delete_files_with_extension(extension):
            pattern = f'./**/*.{extension}'
            
            files = glob.glob(pattern, recursive=True)
            
            for f in files:
                os.remove(f)
                
        delete_files_with_extension('c')
        delete_files_with_extension('o')
        delete_files_with_extension('exe')
        delete_files_with_extension('class')

    def _test_file(self, filename):
        binname = filename.split('.')[0]
        cname = f'{binname}.c'

        # Convert to C
        stdout, stderr, success = self.run_command(f'java Jott ../phase3testcases/{filename} {cname} C')
        self.assertTrue(success, f"Conversion from {filename} to {cname} failed: {stderr} {stdout}")

        # Compile C
        stdout, stderr, success = self.run_command(f'gcc {cname} -o {binname}')
        self.assertTrue(success, f"Compilation of {binname} failed: {stderr} {stdout}")

        # Run the code
        stdout, stderr, success = self.run_command(binname)
        self.assertTrue(success, f"Execution of {binname} failed: {stderr} {stdout}")

        # Print result
        # print(stdout)

    def run_command(self, command):
        try:
            result = subprocess.run(command, shell=True, check=True, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            return result.stdout, result.stderr, True
        except subprocess.CalledProcessError as e:
            return e.stdout, e.stderr, False
        
if __name__ == '__main__':         
    unittest.main()
    