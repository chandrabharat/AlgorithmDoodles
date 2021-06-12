import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class proposeRejectUsers {

    private List<Job> jobs;
    private List<Candidate> candidates;
    private int size;

    public proposeRejectUsers(int size) {
        this.size = size;
        jobs  = new ArrayList<>();
        candidates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            jobs.add(new Job(i));
            candidates.add(new Candidate((char) (97 + i)));
        }

        for (int i = 0; i < size; i++) {
            jobs.get(i).setPreference();
            candidates.get(i).setPreference();
        }
    }

    public boolean matchingComplete() {
        for (Job job : jobs) {
            if (job.hasMatching == false) {
                return false;
            }
        }
        return true;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    class Candidate {
        List<Job> preference;
        char name;
        Integer bestOffer;
        private Candidate(char ch) {
            preference = new ArrayList<>();
            name = ch;
        }

        private void setPreference() {
            HashSet<Job> seen = new HashSet<>();
            for (int i = 0; i < size; i++) {
                Random randomGenerator = new Random();
                Job currJob = jobs.get((randomGenerator.nextInt(size)));
                while (seen.contains(currJob)) {
                    currJob = jobs.get((randomGenerator.nextInt(size)));
                }
                seen.add(currJob);
                preference.add(currJob);
            }
        }

        public void setBestOffer(Job job) {
            bestOffer = preference.indexOf(job);
        }

        public int rankOffer(Job job) {
            return preference.indexOf(job);
        }
    }


    class Job {
        List<Candidate> preference;
        int name;
        boolean hasMatching;
        int pos;
        private Job(int ch) {
            preference = new ArrayList<>();
            name = ch;
            pos = 0;
        }

        private void setPreference() {
            HashSet<Candidate> seen = new HashSet<>();
            for (int i = 0; i < size; i++) {
                Random randomGenerator = new Random();
                Candidate currCandidate = candidates.get(randomGenerator.nextInt(size));
                while (seen.contains(currCandidate)) {
                    currCandidate = candidates.get(randomGenerator.nextInt(size));
                }
                seen.add(currCandidate);
                preference.add(currCandidate);
            }
        }

        public void setHasMatching() {
            hasMatching = !hasMatching;
        }

        public void incrementPos() {
            pos = pos < size - 1 ? pos + 1 : pos;
        }
    }
}
